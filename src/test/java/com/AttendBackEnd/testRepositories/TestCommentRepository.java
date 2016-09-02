package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.CommentOnPost;
import com.AttendBackEnd.repositories.user.CommentOnAPostRepository;
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
public class TestCommentRepository extends AbstractTestNGSpringContextTests
{
    private static final String TAG = "EventContact Test";
    @Autowired
    private CommentOnAPostRepository repo;
    Date mydate = new Date(1996,02,14);
    private Long id;

    @Test
    public void testCreate() throws Exception {
        CommentOnPost createEntity = new CommentOnPost.Builder()
                .post("We having fun at my house")
                .date(mydate)
                .build();
        CommentOnPost insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<CommentOnPost> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        CommentOnPost entity = repo.findOne(3L);
        CommentOnPost updataEntity = new CommentOnPost.Builder()
                .copy(entity)
                .date(mydate)
                .build();
        repo.save(updataEntity);
        CommentOnPost newEntity = repo.save(updataEntity);
        Assert.assertEquals(TAG + " UPDATE ENTITY", newEntity.getPost(),entity.getPost());
    }

    @Test
    public void testDelete () throws Exception
    {
        CommentOnPost commentOnPost = repo.findOne(2L);
        if ( commentOnPost != null)
        {
            Assert.assertNotNull("Before deleting", commentOnPost);
            repo.delete(2L);
            CommentOnPost deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
