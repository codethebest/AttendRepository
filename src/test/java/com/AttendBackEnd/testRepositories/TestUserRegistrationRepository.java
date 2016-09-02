package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.user.UserRegistration;
import com.AttendBackEnd.repositories.user.UserRegistrationRepository;
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
public class TestUserRegistrationRepository extends AbstractTestNGSpringContextTests
{
    private static final String TAG = "EventContact Test";
    @Autowired
    private UserRegistrationRepository repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        UserRegistration createEntity = new UserRegistration.Builder()
                .gender("Male")
                .name("Liyolo")
                .useremail("Leo.moko@gmail.com")
                .newPassword("2566")
                .build();
        UserRegistration insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " Create", insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<UserRegistration> personSet = repo.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }

    @Test
    public void testUpdate() throws Exception {
        UserRegistration entity = repo.findOne(3L);
        UserRegistration updateEntity= new UserRegistration.Builder()
                .copy(entity)
                .newPassword("LiyoloMoko34")
                .build();
        repo.save(updateEntity);
        UserRegistration newEntity = repo.save(updateEntity);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "LiyoloMoko34", newEntity.getNewPassword());

    }

    @Test
    public void testDelete () throws Exception
    {
        UserRegistration userRegistration = repo.findOne(3L);
        if ( userRegistration != null)
        {
            Assert.assertNotNull("Before deleting", userRegistration);
            repo.delete(2L);
            UserRegistration deletedEvent = repo.findOne(3L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}