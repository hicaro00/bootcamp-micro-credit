package com.lizana.microservicecredit.application.usecase;

import com.lizana.microservicecredit.domain.dtos.CreditDto;
import reactor.core.publisher.Mono;

public interface ValidateForCreditService {

   Mono<Boolean> validateClient(CreditDto creditDto);
}
