package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonContact;
import com.AttendBackEnd.factories.person.PersonContactFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Leo on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestPersonContactController extends AbstractJUnit4SpringContextTests{
    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/personcontact";
        RestTemplate restTemplate = new RestTemplate();
        PersonContact event = PersonContactFactory.getPersonContact("Rocky67",125L,"Mama@Email.com","www.google.com/Rocky67");
        restTemplate.postForObject(URL,event, PersonContact.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/personcontact/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PersonContact event = restTemplate.getForObject(URL,PersonContact.class,"4");
        Assert.assertNotNull(event);
        Assert.assertEquals("Rocky67",event.getScreenName());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/personcontact/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PersonContact event = restTemplate.getForObject(URI, PersonContact.class, "3");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/personcontact";
            PersonContact updateEvent = new PersonContact.Builder()
                    .copy(event)
                    .email("leo.moko8@yahoo.com")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            PersonContact updatedEvent = restTemplate.getForObject(URI, PersonContact.class, "3");
            Assert.assertEquals(updatedEvent.getEmail(),"leo.moko8@yahoo.com");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/personcontact";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/personcontact/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"3");
        PersonContact event= restTemplate.getForObject(URI, PersonContact.class, "3");
        Assert.assertNull(event);
    }

}