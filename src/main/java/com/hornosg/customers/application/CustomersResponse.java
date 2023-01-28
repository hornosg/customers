package com.hornosg.customers.application;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.vo.CustomerAddress;
import com.hornosg.customers.domain.vo.CustomerPhone;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CustomersResponse {

    public LinkedHashMap<String, Object> response(LinkedHashMap<String, Object> result)
    {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        List<LinkedHashMap<String, Object>> data = new ArrayList<>();

        response.put("totalRows", result.get("totalRows"));
        response.put("PageSize", result.get("pageSize"));
        response.put("pageNumber", result.get("pageNumber"));
        ((List<Customer>) result.get("data")).forEach(customer -> data.add(new CustomerResponse().response(customer)));
        response.put("data",data);

        return response;
    }
}
