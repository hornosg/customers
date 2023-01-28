package com.hornosg.customers.domain.exceptions;

public class CustomerTypeInvalidException extends IllegalArgumentException
{
    public static final String ERROR_MESSAGE = "Customer Type is invalid";

    public CustomerTypeInvalidException(){
        super(ERROR_MESSAGE);
    }
}
