package com.hornosg.customers.domain;

import com.hornosg.customers.domain.vo.CustomerId;
import com.hornosg.customers.domain.vo.CustomerTenantId;

import java.util.LinkedHashMap;
import java.util.List;

public interface CustomerRepository {
    void save(Customer customer);
    void update(Customer customer);
    Customer find(CustomerId id);
    LinkedHashMap<String, Object> list(CustomerTenantId tenantId, int pageNumber, int pageSize);
    void delete(CustomerId id);
}
