package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.PostAnEvent;
import com.AttendBackEnd.factories.user.PostAnEventFactory;
import com.AttendBackEnd.services.user.impl.PostAnEventService;
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
public class TestPostAnEventService extends AbstractTestNGSpringContextTests{
@Autowired
private PostAnEventService service;

    @Test
    public void testCreate () throws Exception
    {

        PostAnEvent p = PostAnEventFactory.getPostAnEvent("1stSunday","#1stSunday");
        PostAnEvent postAnEvent= service.create(p);
        Assert.assertNotNull(postAnEvent);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<PostAnEvent> commentOnPosts = service.readAll();
        Assert.assertNotNull(" READ ALL", commentOnPosts);
    }

    @Test
    public void testUpdate() throws Exception {
        PostAnEvent entity = service.readById(1L);
        PostAnEvent updateEntity = new PostAnEvent.Builder()
                .copy(entity)
                .post("2ndSunday")
                .build();
        service.create(updateEntity);
        PostAnEvent newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getPost(),"2ndSunday");
    }

    @Test
    public void testDelete () throws Exception
    {
        PostAnEvent eventAddress = service.readById(2L);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            service.delete(eventAddress);
            PostAnEvent deletedEvent = service.readById(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
