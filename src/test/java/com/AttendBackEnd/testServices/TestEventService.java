package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.factories.event.EventFactory;
import com.AttendBackEnd.services.event.impl.EventService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;


/**
 * Created by Leo on 8/23/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestEventService extends AbstractTransactionalTestNGSpringContextTests {
        @Autowired
        private EventService service;

        @Test
        public void testCreateEvent() throws Exception
        {
            Event event = EventFactory.getEvent("#lala","Liyolo","Oa");
            Event savedEvent = service.create(event);

            Assert.assertNotNull(savedEvent);
        }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Event> eventContacts = service.readAll();
        Assert.assertNotNull(eventContacts);
    }

    @Test
    public void testUpdate() throws Exception {
        Event eventContact = service.readById(2L);
        Event updateContact = new Event.Builder()
                .copy(eventContact)
                .host("Rands")
                .build();
        service.create(updateContact);
        Event newEntity = service.create(updateContact);
        Assert.assertEquals(newEntity.getHost(),"Rands");
    }

    @Test
    public void testDelete () throws Exception
    {
        Event eventContact = service.readById(1L);
        if (eventContact != null)
        {
            Assert.assertNotNull(eventContact);
            service.delete(eventContact);
            Event deletedContact = service.readById(1L);
            Assert.assertNull(deletedContact);
        }
    }

}
