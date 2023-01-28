package com.hornosg.customers.application.update;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.CustomerFinder;
import com.hornosg.customers.domain.CustomerRepository;
import com.hornosg.customers.domain.exceptions.CustomerDoesNotExistsException;
import com.hornosg.customers.domain.vo.CustomerAddress;
import com.hornosg.customers.domain.vo.CustomerPhone;
import com.hornosg.customers.domain.vo.CustomerProfile;
import com.hornosg.shared.domain.Utils;

import java.security.NoSuchAlgorithmException;

public class CustomerUpdater {
    private final CustomerFinder finder;
    private final CustomerRepository repository;

    public CustomerUpdater(CustomerRepository repository){
        this.repository = repository;
        this.finder = new CustomerFinder(repository);
    }

    public void invoke(Customer customerChanges) throws NoSuchAlgorithmException {
        Customer customer = finder.invoke(customerChanges.getId());
        if (customer == null) {
            throw new CustomerDoesNotExistsException();
        }

        String profile = customer.getProfile() != null ? customer.getProfile().getValue() : "";
        if (emailIsModified(customer.getEmail().getValue(), customerChanges.getEmail().getValue())){
            String emailHash = Utils.createMD5Hash(customerChanges.getEmail().getValue());
            profile = "https://www.gravatar.com/avatar/"+ emailHash +"?d=identicon";
        }

        Customer customerUpdated = new Customer(
                customer.getId(),
                customer.getTenantId(),
                customerChanges.getName() != null ? customerChanges.getName() : customer.getName(),
                customerChanges.getSurname() != null ? customerChanges.getSurname() : customer.getSurname(),
                customerChanges.getEmail() != null ? customerChanges.getEmail() : customer.getEmail(),
                customerChanges.getAddress() != null ?
                    customerAddressUpdated(customer.getAddress(), customerChanges.getAddress()) : customer.getAddress(),
                customerChanges.getPhone() != null ?
                    customerPhoneUpdated(customer.getPhone(), customerChanges.getPhone()) : customer.getPhone(),
                new CustomerProfile(profile),
                customerChanges.getType() != null ? customerChanges.getType() : customer.getType()
        );

        repository.update(customerUpdated);
    }

    private Boolean emailIsModified(String customerEmail, String commandEmail){
        return customerEmail != commandEmail && commandEmail != null;
    }

    private CustomerAddress customerAddressUpdated(CustomerAddress address, CustomerAddress addressChanges){
        return new CustomerAddress(
            addressChanges.getStreet() != null ? addressChanges.getStreet() : address.getStreet(),
            addressChanges.getNumber() != null ? addressChanges.getNumber() : address.getNumber(),
            addressChanges.getBetweenStreets() != null ? addressChanges.getBetweenStreets() : address.getBetweenStreets(),
            addressChanges.getCity() != null ? addressChanges.getCity() : address.getCity(),
            addressChanges.getPostalCode() != null ? addressChanges.getPostalCode() : address.getPostalCode(),
            addressChanges.getState() != null ? addressChanges.getState() : address.getState(),
            addressChanges.getCountry() != null ? addressChanges.getCountry() : address.getCountry()
        );
    }

    private CustomerPhone customerPhoneUpdated(CustomerPhone phone, CustomerPhone phoneChanges){
        return new CustomerPhone(
            phoneChanges.getCountryCode() != null ? phoneChanges.getCountryCode() : phone.getCountryCode(),
            phoneChanges.getAreaCode() != null ? phoneChanges.getAreaCode() : phone.getAreaCode(),
            phoneChanges.getNumber() != null ? phoneChanges.getNumber() : phone.getNumber()
        );
    }
}
