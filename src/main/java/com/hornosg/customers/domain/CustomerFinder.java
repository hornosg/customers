package com.hornosg.customers.domain;

import com.hornosg.customers.domain.vo.CustomerId;

public class CustomerFinder {

    private CustomerRepository repository;

    public CustomerFinder(CustomerRepository repository){
        this.repository = repository;
    }

    public Customer invoke(CustomerId id){
        return repository.find(id);
    }

}
