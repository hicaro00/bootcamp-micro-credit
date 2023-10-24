package com.lizana.microservicecredit.domain.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.lizana.microservicecredit.infrastructure.outputadapter")
public class reactiveMonogo {

}
