package com.hornosg.customers.domain.vo;

import com.hornosg.customers.domain.enums.CustomerTypes;
import com.hornosg.customers.domain.exceptions.CustomerTypeInvalidException;
import com.hornosg.shared.domain.Utils;
import com.hornosg.shared.domain.vo.StringDomain;

public class CustomerType extends StringDomain {

    public CustomerType(String value) 
    {
        super(value);

        if (Utils.isNullString(value)){
            value = CustomerTypes.EVENTUAL.toString();
            return;
        }

        if (!isValid(value)){
            throw new CustomerTypeInvalidException();
        }
    }

    public boolean isValid(String value) {
        for (CustomerTypes type : CustomerTypes.values()) {
            if (type.name().equals(value.toUpperCase())) {
                return true;
            }
        }

        return false;
    }
}
