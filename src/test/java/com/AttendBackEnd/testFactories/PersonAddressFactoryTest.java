package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.person.PersonAddress;
import com.AttendBackEnd.factories.person.PersonAddressFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonAddressFactoryTest {

    @Test
    public void testPersonAddress()throws Exception
    {
        PersonAddress personAddress = PersonAddressFactory.getAddress("Satellite Drive","Kwezi Park","South Africa","Cape town");
        Assert.assertEquals("Kwezi Park", personAddress.getSub());
    }

    @Test
    public void testPersonAddress2()throws Exception
    {
        PersonAddress personAddress = PersonAddressFactory.getAddress("Satellite Drive", "Kwezi Park", "South Africa", "Cape Town");
        Assert.assertEquals("Cape Town", personAddress.getCity());
    }
}
