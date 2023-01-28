package com.hornosg.customers.infrastructure.persistence;

import com.hornosg.customers.domain.Customer;
import com.hornosg.customers.domain.vo.CustomerAddress;
import com.hornosg.customers.domain.vo.CustomerEmail;
import com.hornosg.customers.domain.vo.CustomerId;
import com.hornosg.customers.domain.vo.CustomerName;
import com.hornosg.customers.domain.vo.CustomerPhone;
import com.hornosg.customers.domain.vo.CustomerProfile;
import com.hornosg.customers.domain.vo.CustomerSurname;
import com.hornosg.customers.domain.vo.CustomerTenantId;
import com.hornosg.customers.domain.vo.CustomerType;
import com.hornosg.shared.domain.vo.AddressBetweenStreets;
import com.hornosg.shared.domain.vo.AddressCity;
import com.hornosg.shared.domain.vo.AddressCountry;
import com.hornosg.shared.domain.vo.AddressNumber;
import com.hornosg.shared.domain.vo.AddressPostalCode;
import com.hornosg.shared.domain.vo.AddressState;
import com.hornosg.shared.domain.vo.AddressStreet;
import com.hornosg.shared.domain.vo.PhoneAreaCode;
import com.hornosg.shared.domain.vo.PhoneCountryCode;
import com.hornosg.shared.domain.vo.PhoneNumber;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public class CustomerMongoSerialization {

    public Bson getCustomerProjections(){
        return Projections.fields(
            Projections.include("tenantId",
                "name",
                "surname",
                "email",
                "address",
                "phone",
                "profile",
                "type")
        );
    }


    public Document customerToDocument(Customer customer)
    {
        Document customerDocument = new Document("_id", customer.getId().getValue())
                .append("tenantId", customer.getTenantId().getValue())
                .append("name", customer.getName().getValue())
                .append("surname", customer.getSurname().getValue())
                .append("email", customer.getEmail().getValue());

        if(customer.getAddress() != null){
            customerDocument.append("address", documentAddressFromCustomer(customer));
        }

        if(customer.getPhone() != null){
            customerDocument.append("phone", documentPhoneFromCustomer(customer));
        }

        if(customer.getProfile() != null){
            customerDocument.append("profile", customer.getProfile().getValue());
        }

        if(customer.getType() != null){
            customerDocument.append("type", customer.getType().getValue());
        }

        return customerDocument;
    }


    public Customer documentToCustomer(Document document)
    {
        Document address = (Document) document.get("address");
        Document phone = (Document) document.get("phone");

        return new Customer(
                new CustomerId(document.get("_id").toString()),
                new CustomerTenantId(document.get("tenantId").toString()),
                new CustomerName(document.get("name").toString()),
                new CustomerSurname(document.get("surname").toString()),
                new CustomerEmail(document.get("email").toString()),
                documentAddressToCustomerAddress(address),
                documentPhoneToCustomerPhone(phone),
                document.get("profile") != null ?
                    new CustomerProfile(document.get("profile").toString()) : null,
                document.get("type") != null ?
                    new CustomerType(document.get("type").toString()) : null
        );

    }


    public CustomerAddress documentAddressToCustomerAddress(Document address){
        return new CustomerAddress(
                new AddressStreet(address.get("street").toString()),
                new AddressNumber((Integer) address.get("number")),
                address.get("betweenStreets") != null ?
                        new AddressBetweenStreets((List<String>) address.get("betweenStreets")) : null,
                address.get("city") != null ?
                        new AddressCity(address.get("city").toString()) : null,
                address.get("postalCode") != null ?
                        new AddressPostalCode(address.get("postalCode").toString()) : null,
                address.get("state") != null ?
                        new AddressState(address.get("state").toString()) : null,
                address.get("country") != null ?
                        new AddressCountry(address.get("country").toString()) : null
        );
    }

    public CustomerPhone documentPhoneToCustomerPhone(Document phone){
        return new CustomerPhone(
                phone.get("countryCode") != null ?
                        new PhoneCountryCode((Integer) phone.get("countryCode")) : null,
                phone.get("areaCode") != null ?
                        new PhoneAreaCode((Integer) phone.get("areaCode")) : null,
                phone.get("number") != null ?
                        new PhoneNumber((Integer) phone.get("number")) : null
        );
    }

    public Document documentAddressFromCustomer(Customer customer){
        return new Document("street", customer.getAddress().getStreet().getValue())
                .append("number", customer.getAddress().getNumber().getValue())
                .append("betweenStreets", customer.getAddress().getBetweenStreets() != null ?
                        customer.getAddress().getBetweenStreets().getValue() : null)
                .append("city", customer.getAddress().getCity() != null ?
                        customer.getAddress().getCity().getValue() : null)
                .append("postalCode", customer.getAddress().getPostalCode() != null ?
                        customer.getAddress().getPostalCode().getValue() : null)
                .append("state", customer.getAddress().getState() != null ?
                        customer.getAddress().getState().getValue() : null)
                .append("country", customer.getAddress().getCountry() != null ?
                        customer.getAddress().getCountry().getValue() : null);
    }

    public Document documentPhoneFromCustomer(Customer customer){
        return new Document("number", customer.getPhone().getNumber().getValue())
                .append("PhoneCountryCode", customer.getPhone().getCountryCode() != null ?
                        customer.getPhone().getCountryCode().getValue() : null)
                .append("PhoneAreaCode", customer.getPhone().getAreaCode() != null ?
                        customer.getPhone().getAreaCode().getValue() : null);
    }

    public Bson updateCustomer(Customer customer){
        return Updates.combine(
                Updates.set("name", customer.getName().getValue()),
                Updates.set("surname", customer.getSurname().getValue()),
                Updates.set("email", customer.getEmail().getValue()),
                Updates.set("address", documentAddressFromCustomer(customer)),
                Updates.set("phone", documentPhoneFromCustomer(customer)),
                Updates.set("profile", customer.getProfile().getValue()),
                Updates.set("type", customer.getType().getValue()),
                //Updates.addToSet("genres", "Sports"),
                Updates.currentTimestamp("lastUpdated"));
    }
}
