package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventAddress;
import com.AttendBackEnd.factories.event.EventAddressFactory;
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
 * Created by Leo on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestEventAddressController extends AbstractTestNGSpringContextTests {

    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/eventaddress";
        RestTemplate restTemplate = new RestTemplate();
        EventAddress eventAddress = EventAddressFactory.getEventAddress("2 Satellite Drve","Kwezi Par","South Africa","Cape Town");
        restTemplate.postForObject(URL,eventAddress, EventAddress.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/eventaddress/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EventAddress event = restTemplate.getForObject(URL,EventAddress.class,"10");
        Assert.assertNotNull(event);
        Assert.assertEquals("cape town",event.getCity());

    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/eventaddress/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EventAddress event = restTemplate.getForObject(URI, EventAddress.class, "12");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/eventaddress";
            EventAddress updateEvent = new EventAddress.Builder()
                    .copy(event)
                    .city("Limpopo")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            EventAddress updatedEvent = restTemplate.getForObject(URI, EventAddress.class, "12");

            Assert.assertEquals(updatedEvent.getCity(),"Limpopo");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/eventaddress";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/eventaddress/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"11");
        EventAddress event= restTemplate.getForObject(URI, EventAddress.class, "11");
        Assert.assertNull(event);
    }

}

