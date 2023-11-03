package com.lizana.microservicecredit.domain.documents;

import com.lizana.microservicecredit.domain.dtos.DepositAmountDto;
import com.lizana.microservicecredit.domain.dtos.OutstandingBalance;
import com.lizana.microservicecredit.domain.dtos.WithdrawalAmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("credits")
public class Credit {

    @Id
    private String creditId; // identificador unico de credito// generar un id aleatorio
    private String customerId; //id del clietne asociado al credito
    private String creditCardId; //identificadort de la tarjeta asociada al credito
    private String creditType; //el tipo de credito  PERSONAL ||EMPRESARIAL
    private String accountStatus; //vip|normal
    private LocalDateTime openingDate;  //fecha en que se crea o aprueva el credito
    private boolean temporaryBlock; //true or false  cuenta bloqueada
    private int numberOfTransactions; //numer de transacciones libres de comicion 20
    private double transactionFee; //comision por trnasacciiones
    private Double interestRate;  // tasa de interes del credito aprovado
    private BigDecimal totalAmount;  // monto total del credito aprobado
    private BigDecimal totalInterest; // interes totales generados por el credito
    private BigDecimal availableCredit; // saldo disponoble del credito ,, actualisacion cosntante
    private List<OutstandingBalance> outstandingBalances = new ArrayList<>(); //saldo pendietne as pagar
    private List<DepositAmountDto> depositAmountDtos = new ArrayList<>(); //deposito o pago de deuda
    private List<WithdrawalAmountDto> withdrawalAmountDtos = new ArrayList<>(); //retiro de saldo del credito

}
