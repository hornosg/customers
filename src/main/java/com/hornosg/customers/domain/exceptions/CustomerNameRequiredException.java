package com.hornosg.customers.domain.exceptions;

public class CustomerNameRequiredException extends IllegalArgumentException
{
    public static final String ERROR_MESSAGE = "Customer Name is Required";

    public CustomerNameRequiredException(){
        super(ERROR_MESSAGE);
    }
}
