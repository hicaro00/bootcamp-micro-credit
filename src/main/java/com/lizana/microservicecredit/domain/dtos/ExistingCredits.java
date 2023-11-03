package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExistingCredits {
  private String creditId;
  private String creditCardId;
  private String bankAccountID;
  private BigDecimal availableCredit;

}
