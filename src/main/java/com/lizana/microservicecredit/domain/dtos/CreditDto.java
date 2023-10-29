package com.lizana.microservicecredit.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
  private BigDecimal outstandingBalance; //saldo pendietne as pagar
  private DepositAmountDto depositAmountDto; //deposito o pago de deuda
  private WithdrawalAmountDto withdrawalAmountDto; //retiro de saldo del credito


  public static CreditDto combineAll(List<CreditDto> creditDtos) {
    if (creditDtos == null || creditDtos.isEmpty()) {
      return new CreditDto(); // Devuelve un CreditDto vacío si la lista está vacía o nula
    }


    CreditDto combinedCreditDto = new CreditDto();

    combinedCreditDto.setCustomerId(creditDtos.get(0).getCustomerId());

    for (CreditDto creditDto : creditDtos) {
      combinedCreditDto.setCustomerId(creditDto.getCustomerId());
      combinedCreditDto.setInterestRate(combinedCreditDto.getInterestRate());
      combinedCreditDto.setCreditType(combinedCreditDto.getCreditType());
      combinedCreditDto.setDate(combinedCreditDto.getDate());
      combinedCreditDto.setTotalAmount(combinedCreditDto.getTotalAmount().add(creditDto.getTotalAmount()));
      combinedCreditDto.setTotalInterest(combinedCreditDto.getTotalInterest().add(creditDto.getTotalInterest()));
      combinedCreditDto.setOutstandingBalance(combinedCreditDto.getOutstandingBalance());

    }

    return combinedCreditDto;
  }

}
