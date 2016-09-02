package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventBasicInformation;
import com.AttendBackEnd.factories.event.EventBasicInformationFactory;
import com.AttendBackEnd.services.event.impl.EventBasicInformationService;
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
public class TestEventBasicInformationService extends AbstractTestNGSpringContextTests{
    @Autowired
    private EventBasicInformationService service;

    @Test
    public void testCreate () throws Exception
    {
        Date startdate = new Date(12,8,2011);
        Date enddate = new Date(13,8,2011);
        EventBasicInformation p = EventBasicInformationFactory.getEventBasicInformation("PoolParty",startdate,enddate);
        EventBasicInformation eventBasicInformation = service.create(p);
        Assert.assertNotNull(eventBasicInformation);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<EventBasicInformation> personSet = service.readAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        EventBasicInformation entity = service.readById(1L);
        EventBasicInformation updateEntity = new EventBasicInformation.Builder()
                .copy(entity)
                .eventtye("Steet Party")
                .build();
        service.create(updateEntity);
        EventBasicInformation newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getEventtype(),"Steet Party");
    }

    @Test
    public void testDelete () throws Exception
    {
        EventBasicInformation eventAddress = service.readById(2L);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            service.delete(eventAddress);
            EventBasicInformation deletedEvent = service.readById(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}

