package com.lizana.microservicecredit.application.serviceimpl;

import com.lizana.microservicecredit.domain.dtos.CustomerDto;
import com.lizana.microservicecredit.infrastructure.imputport.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WebClientServiceImpl implements WebClientService {

  @Autowired
  WebClient webClient;

  private static final Logger log = LoggerFactory.getLogger(WebClientService.class);

  public Mono<CustomerDto> getFromExternalService(String endPoint, String customerId) {
    return webClient.get()
            .uri(endPoint,customerId) // Reemplaza con el endpoint específico
            .retrieve()
            .bodyToMono(CustomerDto.class)
            .onErrorResume(throwable -> {

              return Mono.just(new CustomerDto());
            });
  }

  public Mono<CustomerDto> addCreditInCustomerService(CustomerDto customerDto , String id , String endpoint) {
    return webClient.put()
        .uri(endpoint, id)
        .bodyValue(customerDto)
        .retrieve()
        .bodyToMono(CustomerDto.class);

  }

}
