package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonAddress;
import com.AttendBackEnd.repositories.person.PersonAddressRepository;
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
public class TestPersonAddressRepository extends AbstractTestNGSpringContextTests {

    private static final String TAG = "PersonRepo Test";
    @Autowired
    private PersonAddressRepository repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        PersonAddress createEntity = new PersonAddress.Builder()
                .street("16 Satellite drive")
                .sub("Kwezi park")
                .city("Cape Town")
                .country("South Africa")
                .build();
        PersonAddress insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<PersonAddress> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        PersonAddress entity = repo.findOne(2L);
        PersonAddress updateEntity = new PersonAddress.Builder()
                .copy(entity)
                .sub("Kwezi Park")
                .build();
        repo.save(updateEntity);
        PersonAddress newEntity = repo.save(updateEntity);
        Assert.assertEquals(TAG + " UPDATE AN ENTITY", "Kwezi Park", newEntity.getSub());
    }

    @Test
    public void testDelete () throws Exception
    {
        PersonAddress personaddress = repo.findOne(id);
        if ( personaddress != null)
        {
            Assert.assertNotNull("Before deleting", personaddress);
            repo.delete(id);
            PersonAddress deletedPerson = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedPerson);
        }
    }
}
