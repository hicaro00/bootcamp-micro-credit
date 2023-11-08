package com.lizana.microservicecredit.infrastructure.inputadapter;

import com.lizana.microservicecredit.application.expetion.CustomExeption;
import com.lizana.microservicecredit.domain.dtos.CreditDto;
import com.lizana.microservicecredit.domain.dtos.CustomerDto;
import com.lizana.microservicecredit.domain.dtos.MovementDto;
import com.lizana.microservicecredit.infrastructure.imputport.CreditService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("/v1/credit")
public class CreditController {
    @Autowired
    private CreditService creditService;
  /*  @GetMapping("/allcredits/{id}")
    @ResponseBody
    public Mono<CreditDto> getCreditsForClient(@PathVariable String customerId, @PathVariable Sort.Direction direction){
        Mono<CreditDto> allcredi = creditService.getAllCreditByIdCustomer(customerId, direction);
        return ResponseEntity.status(HttpStatus.FOUND).body(allcredi).getBody();

    }*/

    @GetMapping ("/{id}")
    @ResponseBody
    public Mono<CreditDto> getInfoCreditById (@PathVariable (name = "id") String creditId){
        Mono<CreditDto> credit = creditService.getInfoByIdCredit(creditId);
        return ResponseEntity.status(HttpStatus.FOUND).body(credit).getBody();

    }



    @PostMapping
    @ResponseBody
    public Mono<CustomerDto> creatingCredit(@RequestBody CreditDto creditoNuevo){
        Mono<CustomerDto> newCredit = creditService.addCredit(creditoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCredit).getBody();
    }


    @PutMapping
    @ResponseBody
    public Mono<CreditDto> updateCredit (@RequestBody CreditDto credito,@PathVariable String updated){
        Mono<CreditDto> update = creditService.putInfoOfCredit(credito,updated);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(update).getBody();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCredit (@PathVariable(name = "id") String idcredit){
               return creditService.deleteCreditById(idcredit);
    }
    @ResponseBody
    @PostMapping("/creditpay")
    public Mono<CreditDto> creditPay(@RequestBody MovementDto movementDto){
        return creditService.creditPay(movementDto);
    }

}
