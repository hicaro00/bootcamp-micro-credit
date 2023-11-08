package com.lizana.microservicecredit.application.usecase;

import com.lizana.microservicecredit.domain.dtos.DepositAmountDto;
import com.lizana.microservicecredit.domain.dtos.MovementDto;
import java.time.LocalDate;

public class CreatedDepositPaymet {

   public static DepositAmountDto SetDepositForList(MovementDto movementDto){
     DepositAmountDto depositAmountDto = new DepositAmountDto();
     depositAmountDto.setAmount(movementDto.getAmount());
     depositAmountDto.setAuthorizationCode(movementDto.getAuthorizationCode());
     depositAmountDto.setOriginMovement(movementDto.getOriginMovement());
     depositAmountDto.setDateOfMovement(LocalDate.now());
     depositAmountDto.setMovementId(movementDto.getMovementId());

     return depositAmountDto;
   }
}
