package com.hornosg.customers.domain.exceptions;

public class CustomerTenantInvalidException extends IllegalArgumentException
{
    public static final String ERROR_MESSAGE = "Customer TenantId is invalid";

    public CustomerTenantInvalidException(){
        super(ERROR_MESSAGE);
    }
}
