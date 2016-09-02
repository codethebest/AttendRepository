package com.AttendBackEnd.factories.person;

import com.AttendBackEnd.domain.person.PersonAddress;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonAddressFactory {
    public static PersonAddress getAddress(String street , String sub,String country ,String city)
    {
        return new PersonAddress.Builder()
                .street(street)
                .sub(sub)
                .country(country)
                .city(city)
                .build();
    }
}