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


  private String depositoId; //ide de la operacion
  private String accountDestinyId; // desde donde se deposito ,desde que cuenta || o deq agencia o cajero agencia
  private BigDecimal amount; // monot depositado
  private Date depositDate; // fechA del deposito

}
