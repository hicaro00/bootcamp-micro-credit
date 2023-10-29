package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositAmountDto {


  private String depositoId;
  private String accountDestinyId;
  private BigDecimal amount;
  private Date depositDate;


}
