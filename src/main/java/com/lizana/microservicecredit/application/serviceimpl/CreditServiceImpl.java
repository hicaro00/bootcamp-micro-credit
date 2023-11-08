package com.lizana.microservicecredit.application.serviceimpl;

import com.lizana.microservicecredit.application.creditmaper.CreditMapper;
import com.lizana.microservicecredit.application.expetion.CustomExeption;
import com.lizana.microservicecredit.application.usecase.AccountNumberGeneratorImp;
import com.lizana.microservicecredit.application.usecase.CreatedDepositPaymet;
import com.lizana.microservicecredit.application.usecase.ValidateForCreditService;
import com.lizana.microservicecredit.domain.documents.Credit;
import com.lizana.microservicecredit.domain.dtos.CreditDto;
import com.lizana.microservicecredit.domain.dtos.CustomerDto;
import com.lizana.microservicecredit.domain.dtos.DepositAmountDto;
import com.lizana.microservicecredit.domain.dtos.ExistingCredits;
import com.lizana.microservicecredit.domain.dtos.MovementDto;
import com.lizana.microservicecredit.infrastructure.imputport.CreditService;
import com.lizana.microservicecredit.infrastructure.imputport.WebClientService;
import com.lizana.microservicecredit.infrastructure.outputadapter.CreditRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {
  @Autowired
  CreditRepository creditRepository;
  @Autowired
  WebClientService webClientService;

  @Autowired
  ValidateForCreditService validateForCreditService;

  @Autowired
  WebClient webClient;
  @Value("${ENDPOINT_CUSTOMER_UPDATE}")
  String endpointCustomerUpdate;

  /*
      @Override
      public Mono<CreditDto> getAllCreditByIdCustomer(String customerId, Sort.Direction direction) {
          Sort sort = Sort.by(direction, "date");

          return creditRepository.findAllByCustomerId(customerId, sort)
                  .map(CreditMapper::entityToDto)
                  .collectList()
                  .flatMap(creditDtos -> {
                      if (!creditDtos.isEmpty()) {
                          CreditDto combinedCreditDto = CreditDto.combineAll(creditDtos);
                          return Mono.just(combinedCreditDto);
                      } else {
                          return Mono.empty();
                      }
                  });
      }

  */
  @Override
  public Mono<CreditDto> getInfoByIdCredit(String creditId) {
    return creditRepository.findById(creditId)
        .map(CreditMapper::entityToDto);


  }

  @Override
  public Mono<CreditDto> applyForCredit(CreditDto creditDto) {
    return validateForCreditService.validateClient(creditDto)
        .flatMap(isValid -> {
          if (Boolean.TRUE.equals(isValid)) {

            CreditDto newCredit = new CreditDto();
            newCredit.setCreditId(AccountNumberGeneratorImp.generateAccountNumber());
            newCredit.setInterestRate(creditDto.getInterestRate());
            newCredit.setTotalAmount(creditDto.getTotalAmount());
            newCredit.setCustomerId(creditDto.getCustomerId());
            newCredit.setOpeningDate(LocalDateTime.now());
            newCredit.setTemporaryBlock(false);
            newCredit.setAvailableCredit(creditDto.getTotalAmount());
            newCredit.setNumberOfTransactions(20);

            Credit creditEntity = CreditMapper.dtoToEntity(newCredit);

            return creditRepository.save(creditEntity)
                .map(CreditMapper::entityToDto);
          } else {
            return Mono.empty(); // Crédito denegado
          }
        });

  }

  //este es para agregar mas creditos al un clietne Business
  @Override
  public Mono<CustomerDto> addCredit(CreditDto creditDto) {
    return applyForCredit(creditDto).flatMap(creditDto1 -> {

      ExistingCredits existingCredits = new ExistingCredits();
      existingCredits.setAvailableCredit(creditDto1.getAvailableCredit());
      existingCredits.setBankAccountID(creditDto1.getCreditId());
      existingCredits.setCreditCardId(creditDto1.getCreditCardId());

      CustomerDto customerDtonew = new CustomerDto();
      customerDtonew.setLoans(Collections.singletonList(existingCredits));

      return webClient.put()
          .uri(endpointCustomerUpdate, creditDto1.getCustomerId())
          .bodyValue(customerDtonew)
          .retrieve()
          .bodyToMono(CustomerDto.class);
    });
  }


  @Override
  public Mono<CreditDto> putInfoOfCredit(CreditDto creditDto, String customerId) {

    //buscamos pot Id
    return creditRepository.findById(customerId)
        .flatMap(existingCredit -> {
          existingCredit.setInterestRate(creditDto.getInterestRate());
          return creditRepository.save(existingCredit)
              .map(CreditMapper::entityToDto);
        });

  }


  @Override
  public Mono<Void> deleteCreditById(String creditId) {
    return creditRepository.deleteById(creditId);
  }

  //aui recivimos el pago y regresamos un responcode 200

  @Override
  public Mono<CreditDto> creditPay(MovementDto movementDto) {
    return getInfoByIdCredit(movementDto.getDestinationMovement())
        .flatMap(creditDto -> {
          if (creditDto == null) {
            // Manejar el caso donde el crédito no se encuentra
            return Mono.error(new CustomExeption("Crédito no encontrado"));
          }

          BigDecimal newAmount = creditDto.getTotalInterest().subtract(movementDto.getAmount());
          List<DepositAmountDto> updatedList = new ArrayList<>(creditDto.getDepositAmountDtos());
          updatedList.add(CreatedDepositPaymet.SetDepositForList(movementDto));
          creditDto.setDepositAmountDtos(updatedList);
          creditDto.setTotalInterest(newAmount);

          return creditRepository.save(CreditMapper.dtoToEntity(creditDto))
              .map(CreditMapper::entityToDto)
              .onErrorResume(throwable -> {
                // Manejar el error de la base de datos, si es necesario
                return Mono.error(new CustomExeption("Error al guardar el crédito"));
              });
        })
        .onErrorResume(throwable -> {
          // Manejar el error al obtener información del crédito
          return Mono.error(new CustomExeption("Error al obtener información del crédito"));
        });
  }
}
