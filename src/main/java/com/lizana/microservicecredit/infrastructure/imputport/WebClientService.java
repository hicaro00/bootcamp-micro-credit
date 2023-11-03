package com.lizana.microservicecredit.infrastructure.imputport;

import com.lizana.microservicecredit.domain.dtos.CustomerDto;
import reactor.core.publisher.Mono;

public interface WebClientService {
  Mono<CustomerDto> getFromExternalService(String endPoint, String customerId);
  Mono<CustomerDto> addCreditInCustomerService(CustomerDto customerDto , String id , String endpoint);
}
