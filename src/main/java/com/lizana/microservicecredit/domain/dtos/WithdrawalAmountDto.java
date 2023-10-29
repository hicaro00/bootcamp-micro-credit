package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalAmountDto {


  private String withdrawalId;
  private String originOfPayment;
  private BigDecimal amount;
  private Date dateWithdrawal;

}
