package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.factories.person.PersonFactory;
import com.AttendBackEnd.services.person.impl.PersonService;
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
public class TestPersonService extends AbstractTestNGSpringContextTests{

    @Autowired
    private PersonService service;

    @Test
    public void testCreatePerson() throws Exception
    {
        Person person = PersonFactory.getPerson("liyolo","moko","leo.moko@gmail.com","L123");
        Person savedEvent = service.create(person);

        Assert.assertNotNull(savedEvent);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Person> eventContacts = service.readAll();
        Assert.assertNotNull(eventContacts);
    }

    @Test
    public void testUpdate() throws Exception {
        Person eventContact = service.readById(2L);
        Person updateContact = new Person.Builder()
                .copy(eventContact)
                .email("Leo@gmail.com")
                .build();
        service.create(updateContact);
        Person newEntity = service.create(updateContact);
        Assert.assertEquals(newEntity.getEmail(),"Leo@gmail.com");
    }

    @Test
    public void testDelete () throws Exception
    {
        Person eventContact = service.readById(1L);
        if (eventContact != null)
        {
            Assert.assertNotNull(eventContact);
            service.delete(eventContact);
            Person deletedContact = service.readById(1L);
            Assert.assertNull(deletedContact);
        }
    }

}
