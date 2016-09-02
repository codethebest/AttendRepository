package com.AttendBackEnd.domain.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Leo on 8/13/2016.
 */
@Entity
public class PersonContact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long mobile;
    private String screenName;
    private String website;
    private String email;

    public PersonContact ()
    {

    }
    public PersonContact(Builder builder) {
        this.email = builder.email;
        this.mobile = builder.mobile;
        this.website = builder.website;
        this.screenName = builder.screenName;
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public Long getMobile() {
        return mobile;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {

        private Long mobile;
        private String screenName;
        private String website;
        private String email;
        private Long id;


        public Builder mobile(Long value) {
            this.mobile = value;
            return this;
        }

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder screenName(String value) {
            this.screenName = value;
            return this;
        }

        public Builder website(String value) {
            this.website = value;
            return this;
        }

        public Builder email(String value) {
            this.email = value;
            return this;
        }

        public Builder copy(PersonContact personContact) {
            this.mobile = personContact.mobile;
            this.website = personContact.website;
            this.email = personContact.email;
            this.screenName = personContact.screenName;
            this.id = personContact.id;
            return this;
        }

        public PersonContact build() {
            return new PersonContact(this);
        }

    }
}