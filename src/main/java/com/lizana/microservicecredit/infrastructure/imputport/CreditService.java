package com.lizana.microservicecredit.infrastructure.imputport;

import com.lizana.microservicecredit.domain.dtos.CreditDto;

import com.lizana.microservicecredit.domain.dtos.CustomerDto;
import com.lizana.microservicecredit.domain.dtos.MovementDto;
import reactor.core.publisher.Mono;


public interface CreditService {

  // Mono<CreditDto> getAllCreditByIdCustomer(String customerId, Direction direction);
  Mono<CreditDto> getInfoByIdCredit(String creditId);

  Mono<CreditDto> applyForCredit(CreditDto creditDto);

  Mono<CreditDto> putInfoOfCredit(CreditDto creditDto, String customerId);

  Mono<Void> deleteCreditById(String creditId);

  Mono<CustomerDto> addCredit(CreditDto creditDto);

  Mono<CreditDto> creditPay(MovementDto movementDto);


}
