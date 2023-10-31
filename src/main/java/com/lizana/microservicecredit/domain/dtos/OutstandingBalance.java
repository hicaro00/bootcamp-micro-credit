package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutstandingBalance {
  private Date currentBalance; //fecha del saldo registrado
  private BigDecimal balance; // saldo en es la fecha registrada
}
