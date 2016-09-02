package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventBasicInformation;
import com.AttendBackEnd.factories.event.EventBasicInformationFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Set;

/**
 * Created by Leo on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration

public class TestEventBasicInformationController extends AbstractTestNGSpringContextTests{

    @Test
    public void testCreate() throws Exception
    {
        Date startdate = new Date(12,8,2016);
        Date enddate = new Date(13,8,2016);
        String URL = "http://localhost:8080/api/eventbasicinformation";
        RestTemplate restTemplate = new RestTemplate();
        EventBasicInformation event = EventBasicInformationFactory.getEventBasicInformation("Pool party",startdate,enddate);
        restTemplate.postForObject(URL,event, EventBasicInformation.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/eventbasicinformation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EventBasicInformation event = restTemplate.getForObject(URL,EventBasicInformation.class,"5");
        Assert.assertNotNull(event);
        Assert.assertEquals("PoolParty",event.getEventtype());

    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/eventbasicinformation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EventBasicInformation event = restTemplate.getForObject(URI, EventBasicInformation.class, "8");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/eventbasicinformation";
            EventBasicInformation updateEvent = new EventBasicInformation.Builder()
                    .copy(event)
                    .eventtye("Street Bush")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            EventBasicInformation updatedEvent = restTemplate.getForObject(URI, EventBasicInformation.class, "8");

            Assert.assertEquals(updatedEvent.getEventtype(),"Street Bush");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/eventbasicinformation";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/eventbasicinformation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"6");
        EventBasicInformation event= restTemplate.getForObject(URI, EventBasicInformation.class, "6");
        Assert.assertNull(event);
    }

}
