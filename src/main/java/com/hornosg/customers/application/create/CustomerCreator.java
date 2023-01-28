package com.hornosg.customers.application.create;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerRepository;

public class CustomerCreator {

    private final CustomerRepository repository;

    public CustomerCreator(CustomerRepository repository){
        this.repository = repository;
    }

    public void invoke(Customer customer){
        repository.save(customer);
    }

}
