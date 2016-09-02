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
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String auvalue;

    public Person()
    {

    }

    public Person(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.auvalue= builder.auvalue;
        this.id = builder.id;
    }

    public Long  getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuvalue ()
    {
        return auvalue;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String email;
        private int age;
        private String auvalue;
        private Long  id;

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder id(Long  value) {
            this.id = value;
            return this;
        }

        public Builder auvalue(String value)
        {
            this.auvalue = value;
            return this;
        }

        public Builder surname(String value) {
            this.surname = value;
            return this;
        }

        public Builder email(String value) {
            this.email = value;
            return this;
        }

        public Builder age(int value)
        {
            this.age = value;
            return this;

        }

        public Builder copy(Person value)
        {
            this.name = value.name;
            this.surname = value.surname;
            this.email = value.email;
            this.auvalue = value.auvalue;
            this.id=value.id;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}

