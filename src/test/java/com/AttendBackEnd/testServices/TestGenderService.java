package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.settings.Gender;
import com.AttendBackEnd.factories.settings.GenderFactory;
import com.AttendBackEnd.repositories.settings.GenderRepository;
import com.AttendBackEnd.services.settings.impl.GenderService;
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
public class TestGenderService extends AbstractTestNGSpringContextTests{
        @Autowired
        private GenderService service;

        @Test
        public void testCreate () throws Exception
        {
            Gender p = GenderFactory.getGender("Male");
            Gender gender = service.create(p);
            Assert.assertNotNull(gender);
        }

        @Test
        public void testReadAll() throws Exception {
            Iterable<Gender> genders = service.readAll();
            Assert.assertNotNull(" READ ALL", genders);
        }

        @Test
        public void testUpdate() throws Exception {
            Gender entity = service.readById(5L);
            Gender updateEntity = new Gender.Builder()
                    .copy(entity)
                    .name("Female")
                    .build();
            service.create(updateEntity);
            Gender newEntity = service.create(updateEntity);
            Assert.assertEquals(newEntity.getName(),"Female");
        }

        @Test
        public void testDelete () throws Exception
        {
            Gender eventAddress = service.readById(4L);
            if ( eventAddress != null)
            {
                Assert.assertNotNull("Before deleting", eventAddress);
                service.delete(eventAddress);
                Gender deletedEvent = service.readById(4L);
                Assert.assertNull("Deleted",deletedEvent);
            }
        }
}
