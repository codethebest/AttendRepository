package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.user.UserRegistration;
import com.AttendBackEnd.factories.user.UserRegistrationFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class UserRegistrationFactoryTest {

    @Test
    public void test() throws Exception {
        UserRegistration userRegistration = UserRegistrationFactory.getUserRegistration("Leo78", "12345", "Leo", "Male");
        Assert.assertEquals("12345", userRegistration.getNewPassword());
    }

    @Test
    public void testUpdate() throws Exception {
        UserRegistration userRegistration = UserRegistrationFactory.getUserRegistration("Leo78", "12345", "Leo", "Male");
        UserRegistration userRegistration1 = new UserRegistration.Builder()
                .copy(userRegistration)
                .newPassword("875")
               // .gender("Female")
                .build();
        Assert.assertEquals("875", userRegistration1.getNewPassword());
    }
}
