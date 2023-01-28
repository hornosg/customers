package com.hornosg.customers.domain.vo;

import com.hornosg.customers.domain.exceptions.CustomerTenantInvalidException;
import com.hornosg.shared.domain.vo.Identifier;

public class CustomerTenantId extends Identifier {

    public CustomerTenantId(String value) throws CustomerTenantInvalidException
    {
        super(value);
    }
}
