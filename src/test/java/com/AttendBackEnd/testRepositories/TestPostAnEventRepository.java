package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.PostAnEvent;
import com.AttendBackEnd.repositories.user.PostAnEventRepository;
import javafx.geometry.Pos;
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
public class TestPostAnEventRepository extends AbstractTestNGSpringContextTests { private static final String TAG = "EventContact Test";
        @Autowired
        private PostAnEventRepository repo;
        private Long id;
        Date mydate = new Date(2016,02,8);

        @Test
        public void testCreate() throws Exception {
            PostAnEvent createEntity = new PostAnEvent.Builder()
                    .post("We having fun at my house")
                    .tagline("#funtime")
                    .date(mydate)
                    .build();
            PostAnEvent insertedEntity = repo.save(createEntity);
            id=insertedEntity.getId();
            Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        }

        @Test
        public void testReadAll() throws Exception {
            Iterable<PostAnEvent> personSet = repo.findAll();
            Assert.assertNotNull(" READ ALL", personSet);
        }

        @Test
        public void testUpdate() throws Exception {
            PostAnEvent entity = repo.findOne(2L);
            PostAnEvent updataEntity = new PostAnEvent.Builder()
                    .copy(entity)
                    .date(mydate)
                    .tagline("#FunRide")
                    .build();
            repo.save(updataEntity);
            PostAnEvent newEntity = repo.save(updataEntity);
            Assert.assertEquals(TAG+" UPDATE ENTITY","#FunRide",newEntity.getTagline());
        }

        @Test
        public void testDelete () throws Exception
        {
            PostAnEvent postAnEvent = repo.findOne(4L);
            if ( postAnEvent != null)
            {
                Assert.assertNotNull("Before deleting", postAnEvent);
                repo.delete(4L);
                PostAnEvent deletedEvent = repo.findOne(4L);
                Assert.assertNull("Deleted",deletedEvent);
            }
        }
}