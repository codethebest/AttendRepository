package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonContact;
import com.AttendBackEnd.factories.person.PersonContactFactory;
import com.AttendBackEnd.services.person.impl.PersonContactService;
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
public class TestPersonContactService extends AbstractTestNGSpringContextTests{
    @Autowired
    private PersonContactService service;

    @Test
    public void testCreate () throws Exception
    {
        PersonContact eventContact = PersonContactFactory.getPersonContact("Leo",02145L,"Leo.moko@gmail.com","www.google.com/loylo");
        PersonContact eventContact1 = service.create(eventContact);
        Assert.assertNotNull(eventContact1);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<PersonContact> eventContacts = service.readAll();
        Assert.assertNotNull(eventContacts);
    }

    @Test
    public void testUpdate() throws Exception {
        PersonContact eventContact = service.readById(2L);
        PersonContact updateContact = new PersonContact.Builder()
                .copy(eventContact)
                .email("LiyoloMoko@gmail.com")
                .build();
        service.create(updateContact);
        PersonContact newEntity = service.create(updateContact);
        Assert.assertEquals(newEntity.getEmail(),"LiyoloMoko@gmail.com");
    }

    @Test
    public void testDelete () throws Exception
    {
        PersonContact eventContact = service.readById(1L);
        if (eventContact != null)
        {
            Assert.assertNotNull(eventContact);
            service.delete(eventContact);
            PersonContact deletedContact = service.readById(1L);
            Assert.assertNull(deletedContact);
        }
    }

}
