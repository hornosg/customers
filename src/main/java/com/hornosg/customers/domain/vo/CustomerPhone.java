package com.hornosg.customers.domain.vo;

import com.hornosg.shared.domain.vo.PhoneAreaCode;
import com.hornosg.shared.domain.vo.PhoneCountryCode;
import com.hornosg.shared.domain.vo.PhoneNumber;

public class CustomerPhone {
    private PhoneCountryCode countryCode;
    private PhoneAreaCode areaCode;
    private PhoneNumber number;

    public CustomerPhone (
        PhoneCountryCode countryCode,
        PhoneAreaCode areaCode,
        PhoneNumber number
    ) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.number = number;
    }

    public PhoneCountryCode getCountryCode() {
        return countryCode;
    }

    public PhoneAreaCode getAreaCode() {
        return areaCode;
    }

    public PhoneNumber getNumber() {
        return number;
    }
}
