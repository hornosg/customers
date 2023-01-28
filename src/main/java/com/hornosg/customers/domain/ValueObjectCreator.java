package com.hornosg.customers.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hornosg.customers.domain.vo.CustomerAddress;
import com.hornosg.customers.domain.vo.CustomerEmail;
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
import com.hornosg.shared.domain.vo.Identifier;
import com.hornosg.shared.domain.vo.IntegerDomain;
import com.hornosg.shared.domain.vo.PhoneAreaCode;
import com.hornosg.shared.domain.vo.PhoneCountryCode;
import com.hornosg.shared.domain.vo.PhoneNumber;
import com.hornosg.shared.domain.vo.StringDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValueObjectCreator {
    private final ArrayList<String> errors = new ArrayList<>();
    private final ArrayList<String> addressErrors = new ArrayList<>();
    private final ArrayList<String> phoneErrors = new ArrayList<>();

    public ValueObjectCreator() {
        errors.clear();
        addressErrors.clear();
        phoneErrors.clear();
    }

    public void checkErrors(){
        if (errors.size() > 0){
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }

    public  <T> T newIdentifierVO(String value, Class classType)
    {
        Identifier valueObject = null;
        try {
            valueObject = switch (classType.getSimpleName())
                    {
                        case "CustomerTenantId" -> new CustomerTenantId(value);
                        default -> throw new RuntimeException("Class type not is identifier");
                    };
        }catch (Exception e){
            errors.add(e.getMessage());
        }

        return (T) valueObject;
    }

    public  <T> T newStringVO(String value, Class classType)
    {
        StringDomain valueObject = null;
        try {
            valueObject = switch (classType.getSimpleName())
            {
                case "CustomerName" -> new CustomerName(value);
                case "CustomerSurname" -> new CustomerSurname(value);
                case "CustomerEmail" -> new CustomerEmail(value);
                case "AddressStreet" -> new AddressStreet(value);
                case "AddressCity" -> new AddressCity(value);
                case "AddressPostalCode" -> new AddressPostalCode(value);
                case "AddressState" -> new AddressState(value);
                case "AddressCountry" -> new AddressCountry(value);
                case "CustomerProfile" -> new CustomerProfile(value);
                case "CustomerType" -> new CustomerType(value);
                default -> throw new RuntimeException("Class type not is StringDomain");
            };
        }catch (Exception e){
            errors.add(e.getMessage());
        }

        return (T) valueObject;
    }

    public <T> T newIntegerVO(Integer value, Class classType)
    {
        IntegerDomain valueObject = null;
        try{
            valueObject =
                switch (classType.getSimpleName()) {
                    case "AddressNumber" -> new AddressNumber(value);
                    case "PhoneCountryCode" -> new PhoneCountryCode(value);
                    case "PhoneAreaCode" -> new PhoneAreaCode(value);
                    case "PhoneNumber" -> new PhoneNumber(value);
                    default -> throw new RuntimeException("Class type not is IntegerDomain");
                };
        }catch (Exception e){
            errors.add(e.getMessage());
        }
        
        return (T) valueObject;
    }

    public CustomerAddress newCustomerAddress(String value) throws JsonProcessingException {
        ArrayList<String> addressErrors = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> address = mapper.readValue(value,Map.class);

        CustomerAddress customerAddress = null;
        customerAddress = new CustomerAddress(
            address.get("street") != null ?
                newStringVO(address.get("street").toString(), AddressStreet.class) : null,
            address.get("number") != null ?
                newIntegerVO((Integer) address.get("number"), AddressNumber.class) : null,
            address.get("betweenStreets") != null ?
                newAddressBetweenStreets(address.get("betweenStreets").toString()) : null,
            address.get("city") != null ?
                newStringVO(address.get("city").toString(), AddressCity.class) : null,
            address.get("postalCode") != null ?
                newStringVO(address.get("postalCode").toString(), AddressPostalCode.class) : null,
            address.get("state") != null ?
                newStringVO(address.get("state").toString(), AddressState.class) : null,
            address.get("country") != null ?
                newStringVO(address.get("country").toString(), AddressCountry.class) : null
        );

        if (addressErrors.size() > 0){
            errors.add(String.join(", ", addressErrors));
        }

        return customerAddress;
    }

    private AddressBetweenStreets newAddressBetweenStreets(String value){
        AddressBetweenStreets addressBetweenStreets = null;
        try {
            addressBetweenStreets = new AddressBetweenStreets(List.of(value));
        } catch (Exception e) {
            addressErrors.add(e.getMessage());
        }

        return addressBetweenStreets;
    }

    public CustomerPhone newCustomerPhone(String value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> phone = mapper.readValue(value,Map.class);

        CustomerPhone customerPhone = null;
        customerPhone = new CustomerPhone(
            phone.get("countryCode") != null ?
                newIntegerVO(Integer.valueOf(phone.get("countryCode").toString()), PhoneCountryCode.class) : null,
            phone.get("areaCode") != null ?
                    newIntegerVO(Integer.valueOf(phone.get("areaCode").toString()), PhoneAreaCode.class) : null,
            phone.get("number") != null ?
                    newIntegerVO(Integer.valueOf(phone.get("number").toString()), PhoneNumber.class) : null
        );

        if (phoneErrors.size() > 0){
            errors.add(String.join(", ", phoneErrors));
        }

        return customerPhone;
    }
}
