package com.lizana.microservicecredit.infrastructure.outputadapter;

import com.lizana.microservicecredit.domain.documents.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit,String> {
}
