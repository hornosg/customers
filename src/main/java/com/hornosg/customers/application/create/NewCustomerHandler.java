package com.hornosg.customers.application.create;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.ValueObjectCreator;
import com.hornosg.customers.domain.vo.CustomerEmail;
import com.hornosg.customers.domain.vo.CustomerId;
import com.hornosg.customers.domain.vo.CustomerName;
import com.hornosg.customers.domain.vo.CustomerProfile;
import com.hornosg.customers.domain.vo.CustomerSurname;
import com.hornosg.customers.domain.vo.CustomerTenantId;
import com.hornosg.customers.domain.vo.CustomerType;
import com.hornosg.shared.domain.Utils;

import java.security.NoSuchAlgorithmException;

public class NewCustomerHandler {
    private final ValueObjectCreator voCreator;
    private final CustomerCreator creator;

    public NewCustomerHandler(CustomerRepository repository){
        this.voCreator = new ValueObjectCreator();
        this.creator = new CustomerCreator(repository);
    }

    public void invoke(NewCustomerCommand cmd) throws JsonProcessingException, NoSuchAlgorithmException {
        Customer customer;
        String emailHash = Utils.createMD5Hash(cmd.email);
        String profile = cmd.profile == null ? "https://www.gravatar.com/avatar/"+ emailHash +"?d=identicon" : null;

        customer = new Customer(
            new CustomerId(),
            voCreator.newIdentifierVO(cmd.tenantId, CustomerTenantId.class),
            voCreator.newStringVO(cmd.name, CustomerName.class),
            voCreator.newStringVO(cmd.surname, CustomerSurname.class),
            voCreator.newStringVO(cmd.email, CustomerEmail.class),
            cmd.address != null ? voCreator.newCustomerAddress(cmd.address) : null,
            cmd.phone != null ? voCreator.newCustomerPhone(cmd.phone) : null,
            voCreator.newStringVO(profile, CustomerProfile.class),
            voCreator.newStringVO(cmd.type, CustomerType.class)
        );

        voCreator.checkErrors();

        creator.invoke(customer);
    }
}
