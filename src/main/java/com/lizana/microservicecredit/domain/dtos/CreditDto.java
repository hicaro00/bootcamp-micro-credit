package com.lizana.microservicecredit.domain.dtos;

import io.reactivex.rxjava3.annotations.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private String creditId;
    private String customerId;
    private String creditType;
    private LocalDateTime date;
    private String interestRate;
    private BigDecimal totalAmount;
    private BigDecimal totalInterest;
    private BigDecimal outstandingBalance;


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

        return combinedCreditDto;}

}
