package com.hornosg.customers.application.read;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerFinder;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.exceptions.CustomerDoesNotExistsException;
import com.hornosg.customers.domain.vo.CustomerId;

public class CustomerReader {
    private final CustomerFinder finder;

    public CustomerReader(CustomerRepository repository){
        this.finder = new CustomerFinder(repository);
    }

    public Customer invoke(CustomerId id){
        Customer customer = finder.invoke(id);
        if (customer == null) {
            throw new CustomerDoesNotExistsException();
        }
        return customer;
    }

}
