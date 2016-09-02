package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.factories.event.EventFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)

@WebAppConfiguration
public class TestEventController extends AbstractTestNGSpringContextTests{

    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/event";
        RestTemplate restTemplate = new RestTemplate();
        Event event = EventFactory.getEvent("#Ziyawa","Rands Club","Randslala");
        restTemplate.postForObject(URL,event, Event.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/event/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Event event = restTemplate.getForObject(URL,Event.class,"9");
        Assert.assertNotNull(event);
        Assert.assertEquals("Rands  Club",event.getHost());

    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/event/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Event event = restTemplate.getForObject(URI, Event.class, "8");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/event";
            Event updateEvent = new Event.Builder()
                    .copy(event)
                    .host("Club808")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            Event updatedEvent = restTemplate.getForObject(URI, Event.class, "8");

            Assert.assertNotEquals(updatedEvent.getHost(),"Club808");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/event";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
      String URI =  "http://localhost:8080/api/event/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"8");
        Event event= restTemplate.getForObject(URI, Event.class, "8");
        Assert.assertNull(event);
    }
}
