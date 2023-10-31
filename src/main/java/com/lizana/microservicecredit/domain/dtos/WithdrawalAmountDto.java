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


  private String withdrawalId; //id de la operacion
  private String originOfWithdrawal; //desde donde se iso el retiro   cajero|| o se hiso un pago hacia donde
  private BigDecimal amount; //monto del retiro
  private Date dateWithdrawal; // fecha del retiro

}
