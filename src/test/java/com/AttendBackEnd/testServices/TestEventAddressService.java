package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventAddress;
import com.AttendBackEnd.factories.event.EventAddressFactory;
import com.AttendBackEnd.services.event.impl.EventAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;
import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Leo on 8/21/2016.
 */

@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestEventAddressService extends AbstractTestNGSpringContextTests {

            @Autowired
            private EventAddressService eventAddressService;


        @Test
        public void testCreate () throws Exception
        {
            EventAddress p = EventAddressFactory.getEventAddress("16 satelite","kwezi Part","south afric","cape town");
            EventAddress eventAddress = eventAddressService.create(p);
            assertNotNull(eventAddress);
        }

        @Test(dependsOnMethods = "testCreate")
        public void testReadAll() throws Exception {
            Iterable<EventAddress> personSet = eventAddressService.readAll();
            assertNotNull(" READ ALL", personSet);
        }

        @Test
        public void testUpdate() throws Exception {
            EventAddress entity = eventAddressService.readById(8L);
            EventAddress updateEntity = new EventAddress.Builder()
                    .copy(entity)
                    .sub("Kwezi park")
                    .build();
            eventAddressService.create(updateEntity);
            EventAddress newEntity = eventAddressService.create(updateEntity);
            Assert.assertEquals(" UPDATE AN ENTITY",newEntity.getSub(),entity.getSub());
        }

        @Test
        public void testDelete () throws Exception
        {
            EventAddress eventAddress = eventAddressService.readById(2L);
            if ( eventAddress != null)
            {
                Assert.assertNotNull("Before deleting", eventAddress);
                eventAddressService.delete(eventAddress);
                EventAddress deletedEvent = eventAddressService.readById(2L);
                Assert.assertNull("Deleted",deletedEvent);
            }
        }
}