package com.lizana.microservicecredit.application.serviceimpl;

import com.lizana.microservicecredit.application.creditmaper.CreditMapper;
import com.lizana.microservicecredit.domain.documents.Credit;
import com.lizana.microservicecredit.domain.dtos.CreditDto;
import com.lizana.microservicecredit.infrastructure.imputport.CreditService;
import com.lizana.microservicecredit.infrastructure.outputadapter.CreditRepository;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static com.lizana.microservicecredit.application.creditmaper.CreditMapper.entityToDto;

public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditRepository creditRepository;
    @Override
    public Maybe<CreditDto> getAllCreditByIdCustomer(String customerId) {

        return null;
    }

    @Override
    public Maybe<CreditDto> getInfoByIdCredit(String creditId) {
        return null;
    }

    @Override
    @Transactional
    public Maybe<CreditDto> createCredit(CreditDto creditDto) {

        return Maybe.just(creditDto)
                .flatMap(this::validateCredit) // Validar los datos del crédito si es necesario
                .flatMap((Function<? super CreditDto, ? extends MaybeSource<? extends CreditDto>>) this::saveCredit);     // Guardar el crédito en la base de datos
    }

    private Maybe<CreditDto> validateCredit(CreditDto credit) {

        if (credit.getOutstandingBalance().compareTo (BigDecimal.ZERO) <= 0) {
            return Maybe.error(new RuntimeException("El monto del crédito debe ser mayor que cero."));
        }
        return Maybe.just(credit);
    }

    private Maybe<CreditDto> saveCredit(CreditDto credit) {
        Credit creditEntity = (Credit) CreditMapper.dtoToEntity(credit);

        return Mono.fromCallable(() -> creditRepository.save(creditEntity))
                .flatMap((Mono<Credit> savedCreditEntity) -> {
                    CreditDto savedCreditDto = CreditMapper.entityToDto(savedCreditEntity);
                    return Maybe.just(savedCreditDto);
                });
    }

@Override
    public Maybe<CreditDto> putInfoOfCredit(CreditDto creditDto, String customerId) {
        return null;
    }

    @Override
    public Completable deletCreditByid(String creditId) {
        return null;
    }
}
