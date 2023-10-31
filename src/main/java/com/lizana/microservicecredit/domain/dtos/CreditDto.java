package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
  private String creditId; // identificador unico de credito
  private String customerId; //id del clietne asociado al credito
  private String creditCardId; //identificadort de la tarjeta asociada al credito
  private String bankAccountId; //id de la cuenta bancaria asociada si la hubiera
  private String creditType; //el tipo de credito  PERSONAL ||EMPRESARIAL
  private LocalDateTime date;  //fecha en que se crea o aprueva el credito
  private String interestRate;  // tasa de interes del credito aprovado
  private BigDecimal totalAmount;  // monto total del credito aprobado
  private BigDecimal totalInterest; // interes totales generados por el credito
  private List<OutstandingBalance> outstandingBalances = new ArrayList<>(); //saldo pendietne as pagar
  private List<DepositAmountDto> depositAmountDtos = new ArrayList<>(); //deposito o pago de deuda
  private List<WithdrawalAmountDto> withdrawalAmountDtos = new ArrayList<>(); //retiro de saldo del credito





}
