package com.lizana.microservicecredit.infrastructure.imputport;

import com.lizana.microservicecredit.domain.dtos.CreditDto;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface CreditService {

    public Maybe<CreditDto> getAllCreditByIdCustomer(String customerId);
    public Maybe<CreditDto> getInfoByIdCredit(String creditId);
    public Maybe<CreditDto> createCredit(CreditDto creditDto);
    public Maybe<CreditDto> putInfoOfCredit(CreditDto creditDto, String customerId);
    public Completable deletCreditByid(String creditId);



}
