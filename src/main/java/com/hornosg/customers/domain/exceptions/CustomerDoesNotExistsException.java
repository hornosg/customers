package com.hornosg.customers.domain.exceptions;

import com.hornosg.shared.domain.exceptions.DomainNotFoundException;

public class CustomerDoesNotExistsException extends DomainNotFoundException
{
    public static final String ERROR_MESSAGE = "The Customer does not exists";

    public CustomerDoesNotExistsException(){
        super(ERROR_MESSAGE);
    }
}
