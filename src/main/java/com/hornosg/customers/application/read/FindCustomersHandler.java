package com.hornosg.customers.application.read;

import com.hornosg.customers.application.CustomersResponse;
import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.vo.CustomerTenantId;

import java.util.LinkedHashMap;
import java.util.List;

public class FindCustomersHandler {
    private static final CustomersResponse customersResponse = new CustomersResponse();
    private final CustomersReader list;

    public FindCustomersHandler(CustomerRepository repository){
        this.list = new CustomersReader(repository);
    }

    public LinkedHashMap<String, Object> invoke(FindCustomersQuery cmd)
    {
        LinkedHashMap<String, Object> result = list.invoke(
                new CustomerTenantId(cmd.tenantId),
                cmd.pageNumber,
                cmd.pageSize
        );

        result.put("pageNumber", cmd.pageNumber);
        result.put("pageSize", cmd.pageSize);

        return customersResponse.response(result);
    }
}
