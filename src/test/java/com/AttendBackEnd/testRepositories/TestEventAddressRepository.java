package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.event.EventAddress;
import com.AttendBackEnd.repositories.event.EventAddressRepository;
import org.assertj.core.api.IterableAssert;
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
public class TestEventAddressRepository extends AbstractTestNGSpringContextTests {

    @Autowired
    private EventAddressRepository repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        EventAddress createEntity = new EventAddress.Builder()
                .street("16 Satellite drive")
                .sub("Kwezi park")
                .city("Cape Town")
                .country("South Africa")
                .build();
        EventAddress insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(" CREATE", insertedEntity);

    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<EventAddress> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        EventAddress entity = repo.findOne(8L);
        EventAddress updateEntity = new EventAddress.Builder()
                .copy(entity)
                .sub("Kwezi park")
                .build();
        repo.save(updateEntity);
        EventAddress newEntity = repo.save(updateEntity);
        Assert.assertEquals(" UPDATE AN ENTITY",newEntity.getSub(),entity.getSub());
    }

    @Test
    public void testDelete () throws Exception
    {
        EventAddress eventAddress = repo.findOne(id);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            repo.delete(id);
            EventAddress deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}