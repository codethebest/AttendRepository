package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.CommentOnPost;
import com.AttendBackEnd.factories.user.CommentOnPostFactory;
import com.AttendBackEnd.services.user.impl.CommentOnPostService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by Leo on 8/24/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestCommentService extends AbstractTestNGSpringContextTests{
    @Autowired
    private CommentOnPostService service;

    @Test
    public void testCreate () throws Exception
    {
        CommentOnPost p = CommentOnPostFactory.getCommentPost("having fund");
        CommentOnPost commentOnPost = service.create(p);
        Assert.assertNotNull(commentOnPost);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<CommentOnPost> commentOnPosts = service.readAll();
        Assert.assertNotNull(" READ ALL", commentOnPosts);
    }

    @Test
    public void testUpdate() throws Exception {
        CommentOnPost entity = service.readById(1L);
        CommentOnPost updateEntity = new CommentOnPost.Builder()
                .copy(entity)
                .post("Having Mint Time")
                .build();
        service.create(updateEntity);
        CommentOnPost newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getPost(),"Having Mint Time");
    }

    @Test
    public void testDelete () throws Exception
    {
        CommentOnPost eventAddress = service.readById(2L);
        if ( eventAddress != null)
        {
            Assert.assertNotNull("Before deleting", eventAddress);
            service.delete(eventAddress);
            CommentOnPost deletedEvent = service.readById(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
