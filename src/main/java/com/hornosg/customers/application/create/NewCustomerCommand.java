package com.hornosg.customers.application.create;

public class NewCustomerCommand {
    public String tenantId;
    public String name;
    public String surname;
    public String email;
    public String address;
    public String phone;
    public String profile;
    public String type;

    public NewCustomerCommand(
            String tenantId,
            String name,
            String surname,
            String email,
            String address,
            String phone,
            String profile,
            String type) {
        this.tenantId = tenantId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.profile = profile;
        this.type = type;
    }
}
