package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.domain.event.EventContact;
import com.AttendBackEnd.repositories.event.EventContactRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by Leo on 8/20/2016.
 */

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TestEventContactRepository extends AbstractTestNGSpringContextTests {

    private static final String TAG = "EventContact Test";
    @Autowired
    private EventContactRepository repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        EventContact createEntity = new EventContact.Builder()
                .website("www.moko.com")
                .email("leo.moko8@gmail.com")
                .build();
        EventContact insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<EventContact> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        EventContact entity = repo.findOne(2L);
        EventContact updateEntity = new EventContact.Builder()
                .copy(entity)
                .email("leo.moko8@gmail.com")
                .build();
        repo.save(updateEntity);
        EventContact newEntity = repo.save(updateEntity);
        Assert.assertEquals(TAG + " UPDATE AN ENTITY",newEntity.getEmail(),entity.getEmail());
    }

    @Test
    public void testDelete () throws Exception
    {
        EventContact eventContact = repo.findOne(id);
        if ( eventContact != null)
        {
            Assert.assertNotNull("Before deleting", eventContact);
            repo.delete(id);
            EventContact deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}