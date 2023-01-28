package com.hornosg.customers.application;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.vo.CustomerAddress;
import com.hornosg.customers.domain.vo.CustomerPhone;

import java.util.LinkedHashMap;

public class CustomerResponse {

    public LinkedHashMap<String, Object> response(Customer customer)
    {

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("id", customer.getId().getValue());
        response.put("name", customer.getName().getValue());
        response.put("surname", customer.getSurname().getValue());
        response.put("email", customer.getEmail().getValue());
        response.put("address", customer.getAddress() != null ?
                responseAddress(customer.getAddress()) : null);
        response.put("phone", customer.getPhone() != null ?
                responsePhone(customer.getPhone()) : null);
        response.put("profile", customer.getProfile() != null ?
                customer.getProfile().getValue() : null);
        response.put("type", customer.getType() != null ?
                customer.getType().getValue() : null);

        return response;
    }

    private LinkedHashMap<String, Object> responseAddress(CustomerAddress address){
        LinkedHashMap<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put("street", address.getStreet().getValue());
        addressMap.put("number", address.getNumber().getValue());
        addressMap.put("betweenStreets", address.getBetweenStreets() != null ?
            address.getBetweenStreets().getValue() : null);
        addressMap.put("city", address.getCity() != null ?
            address.getCity().getValue() : null);
        addressMap.put("postalCode", address.getPostalCode() != null ?
            address.getPostalCode().getValue() : null);
        addressMap.put("state", address.getState() != null ?
            address.getState().getValue() : null);
        addressMap.put("country", address.getCountry() != null ?
            address.getCountry().getValue() : null);

        return addressMap;
    }

    private LinkedHashMap<String, Object> responsePhone(CustomerPhone phone){
        LinkedHashMap<String, Object> phoneMap = new LinkedHashMap<>();
        phoneMap.put("countryCode", phone.getCountryCode() != null ?
            phone.getCountryCode().getValue() : null);
        phoneMap.put("areaCode", phone.getAreaCode() != null ?
            phone.getAreaCode().getValue() : null);
        phoneMap.put("number", phone.getNumber().getValue());

        return phoneMap;
    }
}
