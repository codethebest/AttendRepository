package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonBasicInformation;
import com.AttendBackEnd.factories.person.PersonBasicInformationFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Set;

/**
 * Created by Leo on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)

@WebAppConfiguration

public class TestPersonBasicinformationController extends AbstractJUnit4SpringContextTests {
    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/personbasicinformation";
        RestTemplate restTemplate = new RestTemplate();
        PersonBasicInformation event = PersonBasicInformationFactory.getPersonBasicInformation("Male","Gugulethu",8,2016,20,"Partying");
        restTemplate.postForObject(URL,event, PersonBasicInformation.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/personbasicinformation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PersonBasicInformation event = restTemplate.getForObject(URL,PersonBasicInformation.class,"5");
        Assert.assertNotNull(event);
        Assert.assertEquals("Khayelitsha",event.getHometown());

    }

    @Test
    public void testUpdate(){

        String URI =  "http://localhost:8080/api/personbasicinformation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PersonBasicInformation event = restTemplate.getForObject(URI, PersonBasicInformation.class, "2");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/personbasicinformation";
            PersonBasicInformation updateEvent = new PersonBasicInformation.Builder()
                    .copy(event)
                    .hometown("Gugulethu")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            PersonBasicInformation updatedEvent = restTemplate.getForObject(URI, PersonBasicInformation.class, "2");

            Assert.assertEquals(updatedEvent.getHometown(),"Gugulethu");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/personbasicinformation";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/personbasicinformation/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"6");
        PersonBasicInformation event= restTemplate.getForObject(URI, PersonBasicInformation.class, "6");
        Assert.assertNull(event);
    }

}
