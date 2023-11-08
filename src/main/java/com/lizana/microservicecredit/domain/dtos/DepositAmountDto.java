package com.lizana.microservicecredit.domain.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmountDto {


  private String movementId;
  private BigDecimal amount;
  private String originMovement;
  private LocalDate dateOfMovement;
  private String authorizationCode;
}
