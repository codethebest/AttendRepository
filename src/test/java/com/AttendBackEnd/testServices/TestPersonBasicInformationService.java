package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonBasicInformation;
import com.AttendBackEnd.factories.person.PersonBasicInformationFactory;
import com.AttendBackEnd.services.person.impl.PersonBasicInformationService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by Leo on 8/24/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestPersonBasicInformationService extends AbstractTestNGSpringContextTests{
    @Autowired
    private PersonBasicInformationService service;

    @Test
    public void testCreate () throws Exception
    {
        Date startdate = new Date(12,8,2011);
        Date enddate = new Date(13,8,2011);
        PersonBasicInformation p = PersonBasicInformationFactory.getPersonBasicInformation("Male","Lesotho",8,2016,19,"Cricket");
        PersonBasicInformation eventBasicInformation = service.create(p);
        Assert.assertNotNull(eventBasicInformation);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<PersonBasicInformation> personSet = service.readAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        PersonBasicInformation entity = service.readById(1L);
        PersonBasicInformation updateEntity = new PersonBasicInformation.Builder()
                .copy(entity)
                .sex("Female")
                .build();
        service.create(updateEntity);
        PersonBasicInformation newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getSex(),"Female");
    }

    @Test
    public void testDelete () throws Exception
    {
        PersonBasicInformation eventAddress = service.readById(2L);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            service.delete(eventAddress);
            PersonBasicInformation deletedEvent = service.readById(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}



