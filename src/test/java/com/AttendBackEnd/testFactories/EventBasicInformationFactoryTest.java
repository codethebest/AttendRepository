package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.event.EventBasicInformation;
import com.AttendBackEnd.factories.event.EventBasicInformationFactory;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventBasicInformationFactoryTest {

    Date startdate = new Date(12,8,2011);
    Date enddate = new Date(13,8,2011);
    @Test
    public void test()throws Exception
    {
        EventBasicInformation event = EventBasicInformationFactory.getEventBasicInformation("Pool party",startdate,enddate);
        Assert.assertEquals("Pool party", event.getEventtype());
    }

    @Test
    public void test2()throws Exception
    {
        EventBasicInformation event = EventBasicInformationFactory.getEventBasicInformation("Pool party",startdate,enddate);
        Assert.assertNotEquals("bitch party", event.getEventtype());
    }
}
