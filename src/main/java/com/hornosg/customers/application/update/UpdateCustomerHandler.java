package com.hornosg.customers.application.update;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hornosg.customers.application.read.CustomerReader;
import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.ValueObjectCreator;
import com.hornosg.customers.domain.vo.CustomerEmail;
import com.hornosg.customers.domain.vo.CustomerId;
import com.hornosg.customers.domain.vo.CustomerName;
import com.hornosg.customers.domain.vo.CustomerProfile;
import com.hornosg.customers.domain.vo.CustomerSurname;
import com.hornosg.customers.domain.vo.CustomerType;
import com.hornosg.shared.domain.Utils;

import java.security.NoSuchAlgorithmException;

public class UpdateCustomerHandler {
    private final ValueObjectCreator voCreator;
    private final CustomerReader reader;
    private final CustomerUpdater updater;

    public UpdateCustomerHandler(CustomerRepository repository){
        this.voCreator = new ValueObjectCreator();
        this.reader = new CustomerReader(repository);
        this.updater = new CustomerUpdater(repository);
    }

    public void invoke(UpdateCustomerCommand cmd) throws JsonProcessingException, NoSuchAlgorithmException {
        Customer customerUpdated = new Customer(
            new CustomerId(cmd.id),
            null,
            cmd.name != null ? voCreator.newStringVO(cmd.name, CustomerName.class) : null,
            cmd.surname != null ? voCreator.newStringVO(cmd.surname, CustomerSurname.class) : null,
            cmd.email != null ? voCreator.newStringVO(cmd.email, CustomerEmail.class) : null,
            cmd.address != null ? voCreator.newCustomerAddress(cmd.address) : null,
            cmd.phone != null ? voCreator.newCustomerPhone(cmd.phone) : null,
            cmd.profile != null ? voCreator.newStringVO(cmd.profile, CustomerProfile.class) : null,
            cmd.type != null ? voCreator.newStringVO(cmd.type, CustomerType.class) : null
        );

        voCreator.checkErrors();

        updater.invoke(customerUpdated);
    }
}
