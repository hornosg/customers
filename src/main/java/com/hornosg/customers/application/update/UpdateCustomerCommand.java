package com.hornosg.customers.application.update;

public class UpdateCustomerCommand {
    public String id;
    public String name;
    public String surname;
    public String email;
    public String address;
    public String phone;
    public String profile;
    public String type;

    public UpdateCustomerCommand(
            String id,
            String name,
            String surname,
            String email,
            String address,
            String phone,
            String profile,
            String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.profile = profile;
        this.type = type;
    }
}
