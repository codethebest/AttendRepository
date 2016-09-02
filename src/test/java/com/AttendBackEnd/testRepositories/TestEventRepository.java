package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.repositories.event.EventRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.Set;
/**
 * Created by Leo on 8/17/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TestEventRepository extends AbstractTestNGSpringContextTests {

    @Autowired
    private EventRepository repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        Event createEntity = new Event.Builder()
                .name("ZIYAWA")
                .host("RandsClub")
                .tagline("#ZIYAWA")
                .build();
        Event insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(" CREATE", insertedEntity);

    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Event> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        Event entity = repo.findOne(1L);
        Event updateEntity = new Event.Builder()
                .copy(entity)
                .tagline("#ZIYAWA")
                .build();
        repo.save(updateEntity);
        Event updatedEntity = repo.save(updateEntity);
        Assert.assertEquals(" UPDATE AN ENTITY",updatedEntity.getTagline(),updateEntity.getTagline());
    }

    @Test
    public void testDelete () throws Exception
    {
        Event event = repo.findOne(4L);
        if (event != null)
        {
            Assert.assertNotNull("Before deleting",event);
            repo.delete(4L);
            Event deletedEvent = repo.findOne(4L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
