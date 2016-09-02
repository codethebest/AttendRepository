package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.UserRegistration;
import com.AttendBackEnd.factories.user.UserRegistrationFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by Leo on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)

@WebAppConfiguration
public class TestUserRegistrationController extends AbstractJUnit4SpringContextTests{
    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/userregistration";
        RestTemplate restTemplate = new RestTemplate();
        UserRegistration userRegistration = UserRegistrationFactory.getUserRegistration("Leo78", "12345", "Lee", "Male");
        restTemplate.postForObject(URL,userRegistration, UserRegistration.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/userregistration/{id}";
        RestTemplate restTemplate = new RestTemplate();
        UserRegistration event = restTemplate.getForObject(URL,UserRegistration.class,"1");
        Assert.assertNotNull(event);
        Assert.assertEquals("2566",event.getNewPassword());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/userregistration/{id}";
        RestTemplate restTemplate = new RestTemplate();
        UserRegistration event = restTemplate.getForObject(URI, UserRegistration.class, "3");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/userregistration";
            UserRegistration updateEvent = new UserRegistration.Builder()
                    .copy(event)
                    .name("leo")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            UserRegistration updatedEvent = restTemplate.getForObject(URI, UserRegistration.class, "3");

            Assert.assertEquals(updatedEvent.getName(),"leo");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/userregistration";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/userregistration/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"6");
        UserRegistration event= restTemplate.getForObject(URI, UserRegistration.class, "6");
        Assert.assertNull(event);
    }

}

