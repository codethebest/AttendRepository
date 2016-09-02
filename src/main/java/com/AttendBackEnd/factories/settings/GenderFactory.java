package com.AttendBackEnd.factories.settings;


import com.AttendBackEnd.domain.settings.Gender;

/**
 * Created by hashcode on 2016/04/12.
 */
public class GenderFactory {
    public static Gender getGender(String name){
        return new Gender.Builder()
                .name(name)
                .build();

    }
}
