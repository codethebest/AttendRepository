package com.AttendBackEnd.factories.person;

import com.AttendBackEnd.domain.person.Person;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonFactory {
    public static Person getPerson(String name , String surname ,String email ,String password) {
        return new Person.Builder()
                .email(email)
                .name(name)
                .surname(surname)
                .auvalue(password)
                .build();
    }
}
