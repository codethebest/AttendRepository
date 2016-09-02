package com.AttendBackEnd.domain.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Leo on 8/13/2016.
 */
@Entity
public class EventAddress implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String sub;
    private String country;
    private String city;

    public EventAddress (Builder builder)
    {
        this.street = builder.street;
        this.city = builder.city;
        this.country = builder.country;
        this.sub = builder.sub;
        this.id = builder.id;
    }
    public EventAddress(){}
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

        public Builder id(Long value) {
            this.id = value;
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

        public Builder copy(EventAddress eventAddress)
        {
            this.street = eventAddress.street;
            this.sub = eventAddress.sub;
            this.city = eventAddress.city;
            this.country = eventAddress.country;
            this.id=eventAddress.id;
            return this;
        }
        public EventAddress build()
        {
            return new EventAddress(this);
        }
    }

}

