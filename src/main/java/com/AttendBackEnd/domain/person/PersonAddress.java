package com.AttendBackEnd.domain.person;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;



/**
 * Created by Leo on 8/13/2016.
 */
@Entity
public class PersonAddress implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String sub;
    private String country;
    private String city;

    public PersonAddress()
    {

    }
    public PersonAddress (Builder builder)
    {
        this.street = builder.street;
        this.city = builder.city;
        this.country = builder.country;
        this.sub = builder.sub;
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getSub() {
        return sub;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public static class Builder
    {
        private String street;
        private String sub;
        private String country;
        private String city;
        private Long id;
        public Builder street (String value)
        {
            this.street = value;
            return this;
        }

        public Builder sub(String value)
        {
            this.sub = value;
            return this;
        }
        public Builder country(String value)
        {
            this.country = value;
            return this;
        }
        public Builder city(String value)
        {
            this.city = value;
            return this;
        }
        public Builder Id(Long value) {
            this.id = value;
            return this;
        }

        public Builder copy(PersonAddress personAddress)
        {
            this.street = personAddress.street;
            this.sub = personAddress.sub;
            this.city = personAddress.city;
            this.country = personAddress.country;
            this.id = personAddress.id;
            return this;
        }
        public PersonAddress build()
        {
            return new PersonAddress(this);
        }

    }

}
