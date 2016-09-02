package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.factories.event.EventFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventFactoryTest {
    @Test
    public void test()throws Exception
    {
        Event event = EventFactory.getEvent("GoodStaff","Club53","Good Staff");
        Assert.assertEquals("Club53",event.getHost());
    }

    @Test
    public void test2()throws Exception
    {
        Event event = EventFactory.getEvent("GoodStaff", "Club53", "Good Staff");
        Assert.assertEquals("Good Staff",event.getName());
    }
}
