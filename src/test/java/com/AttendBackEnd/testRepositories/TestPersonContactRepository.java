package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonContact;
import com.AttendBackEnd.repositories.person.PersonContactRepository;
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
public class TestPersonContactRepository extends AbstractTestNGSpringContextTests {
        private static final String TAG = "PersonContact Test";
        @Autowired
        private PersonContactRepository repo;
        private Long id;

        @Test
        public void testCreate() throws Exception {
            PersonContact createEntity = new PersonContact.Builder()
                    .screenName("Liyolo")
                    .website("www.moko.com")
                    .email("leo.moko8@gmail.com")
                    .build();
            PersonContact insertedEntity = repo.save(createEntity);
            id = insertedEntity.getId();
            Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        }

        @Test
        public void testReadAll() throws Exception {
            Iterable<PersonContact> personSet = repo.findAll();
            Assert.assertNotNull(" READ ALL", personSet);
        }

        @Test
        public void testUpdate() throws Exception {
            PersonContact entity = repo.findOne(2L);
            PersonContact updateEntity = new PersonContact.Builder()
                    .copy(entity)
                    .email("leo.moko8@gmail.com")
                    .build();
            repo.save(updateEntity);
            PersonContact newEntity = repo.save(updateEntity);
            Assert.assertEquals(TAG + " UPDATE AN ENTITY",newEntity.getEmail(),entity.getEmail());
        }

        @Test
        public void testDelete () throws Exception
        {
            PersonContact personContact = repo.findOne(3L);
            if ( personContact != null)
            {
                Assert.assertNotNull("Before deleting", personContact);
                repo.delete(3L);
                PersonContact deletedEvent = repo.findOne(3L);
                Assert.assertNull("Deleted",deletedEvent);
            }
        }
}