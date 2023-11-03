package com.lizana.microservicecredit.application.usecase;

import com.lizana.microservicecredit.domain.dtos.CreditDto;
import com.lizana.microservicecredit.domain.dtos.CustomerDto;

public interface AddInfoCreditInCustomer {

  CustomerDto addInfoInCustomer(CreditDto creditDto);
}
