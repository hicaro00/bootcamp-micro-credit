package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private String creditId;
    private String customerId;
    private String creditType;
    private String interestRate;
    private BigDecimal outstandingBalance;

}
