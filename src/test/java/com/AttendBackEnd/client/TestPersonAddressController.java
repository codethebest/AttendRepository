package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonAddress;
import com.AttendBackEnd.factories.person.PersonAddressFactory;
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
public class TestPersonAddressController extends AbstractJUnit4SpringContextTests {
    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/api/personaddress";
        RestTemplate restTemplate = new RestTemplate();
        PersonAddress eventAddress = PersonAddressFactory.getAddress("2 Satellite Drve","Kwezi Par","South Africa","Cape Town");
        restTemplate.postForObject(URL,eventAddress, PersonAddress.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/api/personaddress/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PersonAddress event = restTemplate.getForObject(URL,PersonAddress.class,"3");
        Assert.assertNotNull(event);
        Assert.assertEquals("Limpopo",event.getCity());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/api/personaddress/{id}";
        RestTemplate restTemplate = new RestTemplate();
        PersonAddress event = restTemplate.getForObject(URI, PersonAddress.class, "3");
        if(event != null) {
            String UPDATE_URI = "http://localhost:8080/api/personaddress";
            PersonAddress updateEvent = new PersonAddress.Builder()
                    .copy(event)
                    .city("Limpopo")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            PersonAddress updatedEvent = restTemplate.getForObject(URI, PersonAddress.class, "3");

            Assert.assertEquals(updatedEvent.getCity(),"Limpopo");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/api/personaddress";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/api/personaddress/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"6");
        PersonAddress event= restTemplate.getForObject(URI, PersonAddress.class, "6");
        Assert.assertNull(event);
    }

}
