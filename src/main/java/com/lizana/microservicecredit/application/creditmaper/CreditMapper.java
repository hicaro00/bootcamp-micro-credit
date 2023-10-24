package com.lizana.microservicecredit.application.creditmaper;

import com.lizana.microservicecredit.domain.documents.Credit;
import com.lizana.microservicecredit.domain.dtos.CreditDto;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

public class CreditMapper {

    private CreditMapper(){
    }
        public static CreditDto entityToDto(Credit credit) {
            CreditDto creditDto = new CreditDto();
            BeanUtils.copyProperties(credit, creditDto);
            return creditDto;
        }

        public static Credit dtoToEntity(CreditDto creditDto) {
            Credit credit = new Credit();
            BeanUtils.copyProperties(creditDto, credit);
            return credit;
        }

        public static Credit updateEntity(Credit credit, CreditDto creditDto) {
            BeanUtils.copyProperties(creditDto, credit);
            return credit;
        }



}

