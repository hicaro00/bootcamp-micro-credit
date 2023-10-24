package com.lizana.microservicecredit.application.serviceimpl;

import com.lizana.microservicecredit.application.creditmaper.CreditMapper;
import com.lizana.microservicecredit.domain.documents.Credit;
import com.lizana.microservicecredit.domain.dtos.CreditDto;
import com.lizana.microservicecredit.infrastructure.imputport.CreditService;
import com.lizana.microservicecredit.infrastructure.outputadapter.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditRepository creditRepository;
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
    public Mono<CreditDto> createCredit(CreditDto creditDto) {

        Credit creditEntity = CreditMapper.dtoToEntity(creditDto);//mapear

        return creditRepository.save(creditEntity)
                .map(CreditMapper::entityToDto);

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
}
