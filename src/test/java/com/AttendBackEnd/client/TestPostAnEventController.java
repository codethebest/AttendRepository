package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.PostAnEvent;
import com.AttendBackEnd.factories.user.PostAnEventFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
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

public class TestPostAnEventController extends AbstractJUnit4SpringContextTests {

    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/postanevent";
        RestTemplate restTemplate = new RestTemplate();
        PostAnEvent event = PostAnEventFactory.getPostAnEvent("LOLO","#Holo");
        restTemplate.postForObject(URL,event, PostAnEvent.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/postanevent/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PostAnEvent event = restTemplate.getForObject(URL,PostAnEvent.class,"1");
        Assert.assertNotNull(event);
        Assert.assertEquals("2ndSunday",event.getPost());

    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/postanevent/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PostAnEvent event = restTemplate.getForObject(URI, PostAnEvent.class, "5");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/postanevent";
            PostAnEvent updateEvent = new PostAnEvent.Builder()
                    .copy(event)
                    .post("Wish i was the")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            PostAnEvent updatedEvent = restTemplate.getForObject(URI, PostAnEvent.class, "5");

            Assert.assertEquals(updatedEvent.getPost(),"Wish i was the");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/postanevent";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/postanevent/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"6");
        PostAnEvent event= restTemplate.getForObject(URI, PostAnEvent.class, "6");
        Assert.assertNull(event);
    }
}
