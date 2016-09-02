package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventContact;
import com.AttendBackEnd.factories.event.EventContactFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Leo on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)

@WebAppConfiguration
public class TestEventContactController extends AbstractTestNGSpringContextTests{

    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/eventcontact";
        RestTemplate restTemplate = new RestTemplate();
        EventContact event = EventContactFactory.getEventContact("0795710813","leo.moko8@gmail.com","www.google.com");
        restTemplate.postForObject(URL,event, EventContact.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/eventcontact/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EventContact event = restTemplate.getForObject(URL,EventContact.class,"3");
        Assert.assertNotNull(event);
        Assert.assertEquals("0795710813",event.getPhone());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/eventcontact/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EventContact event = restTemplate.getForObject(URI, EventContact.class, "3");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/eventcontact";
            EventContact updateEvent = new EventContact.Builder()
                    .copy(event)
                    .email("leo.moko8@yahoo.com")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            EventContact updatedEvent = restTemplate.getForObject(URI, EventContact.class, "3");
            Assert.assertEquals(updatedEvent.getEmail(),"leo.moko8@yahoo.com");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/eventcontact";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/eventcontact/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"8");
        EventContact event= restTemplate.getForObject(URI, EventContact.class, "8");
        Assert.assertNull(event);
    }

}
