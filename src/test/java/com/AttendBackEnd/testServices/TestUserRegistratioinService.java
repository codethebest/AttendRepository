package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.UserRegistration;
import com.AttendBackEnd.factories.user.UserRegistrationFactory;
import com.AttendBackEnd.services.user.impl.UserRegistrationService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by Leo on 8/24/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestUserRegistratioinService extends AbstractTestNGSpringContextTests{
    @Autowired
    private UserRegistrationService service;

    @Test
    public void testCreate () throws Exception
    {   UserRegistration userRegistration = UserRegistrationFactory.getUserRegistration("Leo78", "12345", "Leo", "Male");
        UserRegistration commentOnPost = service.create(userRegistration);
        Assert.assertNotNull(commentOnPost);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<UserRegistration> commentOnPosts = service.readAll();
        Assert.assertNotNull(" READ ALL", commentOnPosts);
    }

    @Test
    public void testUpdate() throws Exception {
        UserRegistration entity = service.readById(1L);
        UserRegistration updateEntity = new UserRegistration.Builder()
                .copy(entity)
                .gender("Female")
                .build();
        service.create(updateEntity);
        UserRegistration newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getGender(),"Female");
    }

    @Test
    public void testDelete () throws Exception
    {
        UserRegistration eventAddress = service.readById(2L);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            service.delete(eventAddress);
            UserRegistration deletedEvent = service.readById(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}


