package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventContact;
import com.AttendBackEnd.factories.event.EventContactFactory;
import com.AttendBackEnd.services.event.impl.EventContactService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import org.testng.annotations.Test;

/**
 * Created by Leo on 8/24/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestEventContactService extends AbstractTestNGSpringContextTests {
    @Autowired
    private EventContactService service;

    @Test
    public void testCreate () throws Exception
    {
        EventContact eventContact = EventContactFactory.getEventContact("0795710813","Leo.moko8@gmail.com","www.google.com/leo");
        EventContact eventContact1 = service.create(eventContact);
        Assert.assertNotNull(eventContact1);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<EventContact> eventContacts = service.readAll();
        Assert.assertNotNull(eventContacts);
    }

    @Test
    public void testUpdate() throws Exception {
        EventContact eventContact = service.readById(2L);
        EventContact updateContact = new EventContact.Builder()
                .copy(eventContact)
                .email("LiyoloMoko@gmail.com")
                .build();
        service.create(updateContact);
        EventContact newEntity = service.create(updateContact);
        Assert.assertEquals(newEntity.getEmail(),"LiyoloMoko@gmail.com");
    }

    @Test
    public void testDelete () throws Exception
    {
            EventContact eventContact = service.readById(1L);
            if (eventContact != null)
            {
                Assert.assertNotNull(eventContact);
                service.delete(eventContact);
                EventContact deletedContact = service.readById(1L);
                Assert.assertNull(deletedContact);
            }
    }
}