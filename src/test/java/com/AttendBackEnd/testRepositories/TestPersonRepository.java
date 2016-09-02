package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.repositories.person.PersonRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by Leo on 8/20/2016.
 */
@SpringApplicationConfiguration (classes = App.class)
@WebAppConfiguration
public class TestPersonRepository extends AbstractTestNGSpringContextTests {

    private static final String TAG = "PersonRepo Test";
    @Autowired
    private PersonRepository repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        Person createEntity = new Person.Builder()
                .name("Liyolo")
                .surname("Moko")
                .email("leo.moko8@gmail.com")
                .auvalue("4we")
                .build();
        Person insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Person> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        Person entity = repo.findOne(1L);
        Person updateEntity = new Person.Builder()
                .copy(entity)
                .email("leo.moko8@gmail.com")
                .build();
        repo.save(updateEntity);
        Person newEntity = repo.save(updateEntity);
        Assert.assertEquals(TAG+ " UPDATE AN ENTITY",newEntity.getEmail(),entity.getEmail());
    }

    @Test
    public void testDelete () throws Exception
    {
        Person person = repo.findOne(id);
        if ( person != null)
        {
            Assert.assertNotNull("Before deleting", person);
            repo.delete(id);
            Person deletedPerson = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedPerson);
        }
    }
}
