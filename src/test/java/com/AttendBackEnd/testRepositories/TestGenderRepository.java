package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.settings.Gender;
import com.AttendBackEnd.repositories.settings.GenderRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by Leo on 8/21/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestGenderRepository extends AbstractTestNGSpringContextTests{
    private static final String TAG = "EventContact Test";
        @Autowired
        private GenderRepository repo;
        private Long id;

        @Test
        public void testCreate() throws Exception {
            Gender createEntity = new Gender.Builder()
                    .name("Male")
                    .build();
            Gender insertedEntity = repo.save(createEntity);
            id=insertedEntity.getId();
            Assert.assertNotNull(TAG + " CREATE", insertedEntity);
        }

        @Test
        public void testReadAll() throws Exception {
            Iterable<Gender> personSet = repo.findAll();
            Assert.assertNotNull(" READ ALL", personSet);
        }

        @Test
        public void testUpdate() throws Exception {
            Gender entity = repo.findOne(3L);
            Gender updataEntity = new Gender.Builder()
                    .copy(entity)
                    .name("Female")
                    .build();
            repo.save(updataEntity);
            Gender newEntity = repo.save(updataEntity);
            Assert.assertEquals(TAG + " UPDATE ENTITY", newEntity.getName(),"Female");
        }

        @Test
        public void testDelete () throws Exception
        {
            Gender gender = repo.findOne(2L);
            if ( gender != null)
            {
                Assert.assertNotNull("Before deleting", gender);
                repo.delete(2L);
                Gender deletedEvent = repo.findOne(2L);
                Assert.assertNull("Deleted",deletedEvent);
            }
        }
}
