package com.hornosg.customers.application.read;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerFinder;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.exceptions.CustomerDoesNotExistsException;
import com.hornosg.customers.domain.vo.CustomerId;
import com.hornosg.customers.domain.vo.CustomerTenantId;

import java.util.LinkedHashMap;
import java.util.List;

public class CustomersReader {
    private CustomerRepository repository;

    CustomersReader(CustomerRepository repository){
        this.repository = repository;
    }

    public LinkedHashMap<String, Object> invoke(CustomerTenantId tenantId, int pageNumber, int pageSize){
        LinkedHashMap<String, Object> result = repository.list(tenantId, pageNumber, pageSize);
        if (((Long) result.get("totalRows")) == 0) {
            throw new CustomerDoesNotExistsException();
        }

        return result;
    }

}
