package com.hornosg.customers.domain;

import com.hornosg.customers.domain.vo.*;

public class
Customer {
    private CustomerId id;
    private CustomerTenantId tenantId;
    private CustomerName name;
    private CustomerSurname surname;
    private CustomerEmail email;
    private CustomerAddress address;
    private CustomerPhone phone;
    private CustomerProfile profile;
    private CustomerType type;

    public Customer(
            CustomerId id,
            CustomerTenantId tenantId,
            CustomerName name,
            CustomerSurname surname,
            CustomerEmail email,
            CustomerAddress address,
            CustomerPhone phone,
            CustomerProfile profile,
            CustomerType type
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.profile = profile;
        this.type = type;
    }

    public CustomerId getId() {
        return id;
    }

    public CustomerTenantId getTenantId() {
        return tenantId;
    }

    public CustomerName getName() {
        return name;
    }

    public CustomerSurname getSurname() {
        return surname;
    }

    public CustomerEmail getEmail() {
        return email;
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public CustomerPhone getPhone() {
        return phone;
    }

    public CustomerProfile getProfile() {
        return profile;
    }

    public CustomerType getType() {
        return type;
    }
}
