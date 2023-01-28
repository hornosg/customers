package com.hornosg.customers.domain.vo;

import com.hornosg.customers.domain.exceptions.CustomerSurnameRequiredException;
import com.hornosg.shared.domain.vo.StringDomainNotNull;

public class CustomerSurname extends StringDomainNotNull {

    public CustomerSurname(String value) throws CustomerSurnameRequiredException
    {
        super(value);
    }
}
