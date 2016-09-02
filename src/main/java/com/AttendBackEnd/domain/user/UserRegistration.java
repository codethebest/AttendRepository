package com.AttendBackEnd.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Leo on 8/13/2016.
 */
@Entity
public class UserRegistration implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String useremail;
    private String newPassword;
    private String gender;

    public UserRegistration()
    {

    }
    public UserRegistration (Builder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
        this.gender = builder.gender;
        this.useremail = builder.useremail;
        this.newPassword = builder.newPassword;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getGender() {
        return gender;
    }

    public static class Builder
    {
        private String name;
        private String useremail;
        private String newPassword;
        private String gender;
        private Long id;


        public Builder name (String value)
        {
            this.name = value;
            return this;
        }

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }
        public Builder useremail (String value)
        {
            this.useremail = value;
            return this;
        }

        public Builder newPassword (String value)
        {
            this.newPassword = value;
            return this;
        }

        public Builder gender (String value)
        {
            this.gender = value;
            return this;
        }


        public Builder copy(UserRegistration userRegistration)
        {
            this.id=userRegistration.id;
            this.name = userRegistration.name;
            this.gender = userRegistration.gender;
            this.newPassword = userRegistration.newPassword;
            this.useremail = userRegistration.useremail;

            return this;
        }

        public UserRegistration build()
        {
            return new UserRegistration(this);
        }

    }

}
