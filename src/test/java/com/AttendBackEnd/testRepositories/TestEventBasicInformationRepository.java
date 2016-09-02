package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.domain.event.EventBasicInformation;
import com.AttendBackEnd.repositories.event.EventBasicinformationRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by Leo on 8/21/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestEventBasicInformationRepository extends AbstractTestNGSpringContextTests{


    @Autowired
    private EventBasicinformationRepository repo;
    private Long id;
    Date mydate = new Date(2016,02,8);

    @Test
    public void testCreate ()throws Exception
    {
        EventBasicInformation entity = new EventBasicInformation.Builder()
                .start(mydate)
                .end(mydate)
                .eventtye("PoolParty")
                .build();
        EventBasicInformation insertEntity = repo.save(entity);
        id = insertEntity.getId();
        Assert.assertNotNull("Create and Event",insertEntity);
    }

    @Test
    public void testReadAll ()throws Exception
    {
        Iterable <EventBasicInformation> eventlist = repo.findAll();
        Assert.assertNotNull(eventlist);
    }

    @Test
    public void testUpdate () throws Exception
    {
        EventBasicInformation entity = repo.findOne(3L);
        EventBasicInformation entitybuild = new EventBasicInformation.Builder()
                                .copy(entity)
                                .eventtye("House Party")
                                .build();
        repo.save(entitybuild);
        EventBasicInformation newEntity = repo.save(entitybuild);
        Assert.assertEquals("Test update", newEntity.getEventtype(),"House Party");
    }

    @Test
    public void testDelete () throws Exception
    {
        EventBasicInformation eventBasicInformation = repo.findOne(2L);
        if ( eventBasicInformation != null)
        {
            Assert.assertNotNull("Before deleting", eventBasicInformation);
            repo.delete(2L);
            EventBasicInformation deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
