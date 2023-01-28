package com.hornosg.customers.domain.vo;

import com.hornosg.customers.domain.exceptions.CustomerNameRequiredException;
import com.hornosg.shared.domain.vo.StringDomainNotNull;

public class CustomerName extends StringDomainNotNull {
    public CustomerName(String value) throws CustomerNameRequiredException
    {
        super(value);
    }
}
