package com.hornosg.customers.domain.vo;

import com.hornosg.shared.domain.vo.*;

public class CustomerAddress {
    private AddressStreet street;
    private AddressNumber number;
    private AddressBetweenStreets betweenStreets;
    private AddressCity city;
    private AddressPostalCode postalCode;
    private AddressState state;
    private AddressCountry country;

    public CustomerAddress (
            AddressStreet street,
            AddressNumber number,
            AddressBetweenStreets betweenStreets,
            AddressCity city,
            AddressPostalCode postalCode,
            AddressState state,
            AddressCountry country
    ) {
        this.street = street;
        this.number = number;
        this.betweenStreets = betweenStreets;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
    }

    public AddressStreet getStreet() {
        return street;
    }

    public AddressNumber getNumber() {
        return number;
    }

    public AddressBetweenStreets getBetweenStreets() {
        return betweenStreets;
    }

    public AddressCity getCity() {
        return city;
    }

    public AddressPostalCode getPostalCode() {
        return postalCode;
    }

    public AddressState getState() {
        return state;
    }

    public AddressCountry getCountry() {
        return country;
    }
}
