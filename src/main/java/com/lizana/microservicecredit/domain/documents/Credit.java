package com.lizana.microservicecredit.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("credits")
public class Credit {

    @Id
    private String creditId;
    private String customerId;
    private String creditType;
    private LocalDateTime date;
    private String interestRate;
    private BigDecimal totalAmount;
    private BigDecimal totalInterest;
    private BigDecimal outstandingBalance;





}
