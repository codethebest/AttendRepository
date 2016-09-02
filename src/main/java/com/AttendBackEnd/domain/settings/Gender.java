package com.AttendBackEnd.domain.settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Leo on 8/13/2016.
 */
@Entity
public class Gender implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Gender ()
    {

    }

    public  Gender(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static class Builder{
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder state(String state) {

            return this;
        }
        public Builder copy(Gender gender){
            this.id = gender.id;
            this.name = gender.name;


            return this;
        }
        public Gender build(){
            return new Gender(this);
        }
    }
}

