package com.lizana.microservicecredit.application.creditmaper;

import com.lizana.microservicecredit.domain.documents.Credit;
import com.lizana.microservicecredit.domain.dtos.CreditDto;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

public class CreditMapper {

    private CreditMapper(){

    }

    public static Maybe<CreditDto> entityToDto(Maybe<Credit> credit) {
        return credit.flatMap(creditEntity -> {
            CreditDto creditDto = new CreditDto();
            BeanUtils.copyProperties(creditEntity, creditDto);
            return Maybe.just(creditDto);
        });
    }

    public static Maybe<Credit> dtoToEntity(CreditDto creditDto) {
        Credit credit = new Credit();
        BeanUtils.copyProperties(creditDto, credit);
        return Maybe.just(credit);
    }


}

