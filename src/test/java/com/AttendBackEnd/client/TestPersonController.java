package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.factories.person.PersonFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by Leo on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestPersonController extends AbstractJUnit4SpringContextTests{

    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/person";
        RestTemplate restTemplate = new RestTemplate();
        Person event = PersonFactory.getPerson("Liyolo","Surname","Email","Liyoee");
        restTemplate.postForObject(URL,event,Person.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/person/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Person event = restTemplate.getForObject(URL,Person.class,"3");
        Assert.assertNotNull(event);
        Assert.assertEquals("Liyolo",event.getName());

    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/person/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Person event = restTemplate.getForObject(URI, Person.class, "2");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/person";
            Person updateEvent = new Person.Builder()
                    .copy(event)
                    .name("Leo")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            Person updatedEvent = restTemplate.getForObject(URI, Person.class, "2");

            Assert.assertNotEquals(updatedEvent.getName(),"Leo");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/person";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/person/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"2");
        Person event= restTemplate.getForObject(URI, Person.class, "8");
        Assert.assertNull(event);
    }
}


