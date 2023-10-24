package com.lizana.microservicecredit.infrastructure.imputport;

import com.lizana.microservicecredit.domain.dtos.CreditDto;

import org.springframework.data.domain.Sort.Direction;

import reactor.core.publisher.Mono;


public interface CreditService {

   // Mono<CreditDto> getAllCreditByIdCustomer(String customerId, Direction direction);
    Mono<CreditDto> getInfoByIdCredit(String creditId);
    Mono<CreditDto> createCredit(CreditDto creditDto);
    Mono<CreditDto> putInfoOfCredit(CreditDto creditDto, String customerId);
    Mono <Void>deleteCreditById(String creditId);


}
