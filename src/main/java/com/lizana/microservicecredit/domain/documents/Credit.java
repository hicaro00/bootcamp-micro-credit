package com.lizana.microservicecredit.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("credits")
public class Credit extends Mono<Credit> {

    @Id
    private String creditId;
    private String customerId;
    private String creditType;//personal empresarial
    private Double interestRate;
    private BigDecimal outstandingBalance;

    @Override
    public void subscribe(CoreSubscriber<? super Credit> coreSubscriber) {

    }
}
