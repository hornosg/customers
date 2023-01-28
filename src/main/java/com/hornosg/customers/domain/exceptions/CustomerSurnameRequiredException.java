package com.hornosg.customers.domain.exceptions;

public class CustomerSurnameRequiredException extends IllegalArgumentException
{
    public static final String ERROR_MESSAGE = "Customer Surname is Required";

    public CustomerSurnameRequiredException(){
        super(ERROR_MESSAGE);
    }
}
