package com.hornosg.customers.application.read;

import com.hornosg.customers.application.CustomerResponse;
import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.vo.CustomerId;

import java.util.Map;

public class FindCustomerHandler {
    private static final CustomerResponse customerResponse = new CustomerResponse();
    private final CustomerReader reader;

    public FindCustomerHandler(CustomerRepository repository){
        this.reader = new CustomerReader(repository);
    }

    public Map<String, Object> invoke(FindCustomerQuery cmd)
    {
        Customer customer = reader.invoke(new CustomerId(cmd.id));

        return customerResponse.response(customer);
    }
}
