package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonAddress;
import com.AttendBackEnd.factories.person.PersonAddressFactory;
import com.AttendBackEnd.services.person.impl.PersonAddressService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Leo on 8/24/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestPersonAddressService extends AbstractTestNGSpringContextTests{
    @Autowired
    private PersonAddressService personAddressService;


    @Test
    public void testCreate () throws Exception
    {
        PersonAddress p = PersonAddressFactory.getAddress("16 satelite","kwezi Part","south afric","cape town");
        PersonAddress eventAddress = personAddressService.create(p);
        assertNotNull(eventAddress);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testReadAll() throws Exception {
        Iterable<PersonAddress> personSet = personAddressService.readAll();
        assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        PersonAddress entity = personAddressService.readById(3L);
        PersonAddress updateEntity = new PersonAddress.Builder()
                .copy(entity)
                .sub("Kwezi park")
                .build();
        personAddressService.create(updateEntity);
        PersonAddress newEntity = personAddressService.create(updateEntity);
        Assert.assertEquals(" UPDATE AN ENTITY",newEntity.getSub(),entity.getSub());
    }

    @Test
    public void testDelete () throws Exception
    {
        PersonAddress eventAddress = personAddressService.readById(2L);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            personAddressService.delete(eventAddress);
            PersonAddress deletedEvent = personAddressService.readById(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
