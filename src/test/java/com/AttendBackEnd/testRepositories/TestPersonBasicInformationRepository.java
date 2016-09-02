package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.person.PersonBasicInformation;
import com.AttendBackEnd.factories.person.PersonBasicInformationFactory;
import com.AttendBackEnd.repositories.person.PersonBasicInformationRepository;
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
public class TestPersonBasicInformationRepository extends AbstractTestNGSpringContextTests{

    @Autowired
    private PersonBasicInformationRepository repo;
    private Long id;
    Date mydate = new Date(2016,02,8);

    @Test
    public void testCreate ()throws Exception
    {
        PersonBasicInformation personBasicInformation = PersonBasicInformationFactory.getPersonBasicInformation("Male","Khayelitsha",06,2016,19,"Cricket");
        Assert.assertEquals("Male", personBasicInformation.getSex());
        PersonBasicInformation insertEntity = repo.save(personBasicInformation);
        id = insertEntity.getId();
        Assert.assertNotNull("Create and Event",insertEntity);
    }

    @Test
    public void testReadAll ()throws Exception
    {
        Iterable <PersonBasicInformation> eventlist = repo.findAll();
        Assert.assertNotNull(eventlist);
    }

    @Test
    public void testUpdate () throws Exception
    {
        PersonBasicInformation entity = repo.findOne(3L);
        PersonBasicInformation entitybuild = new PersonBasicInformation.Builder()
                .copy(entity)
                .age(20)
                .build();
        repo.save(entitybuild);
        PersonBasicInformation newEntity = repo.save(entitybuild);
        Assert.assertEquals("Test update", newEntity.getAge(),20);
    }

    @Test
    public void testDelete () throws Exception
    {
        PersonBasicInformation personBasicInformation = repo.findOne(2L);
        if ( personBasicInformation != null)
        {
            Assert.assertNotNull("Before deleting", personBasicInformation);
            repo.delete(2L);
            PersonBasicInformation deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }




}
