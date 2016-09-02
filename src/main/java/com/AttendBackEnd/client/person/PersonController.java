package com.AttendBackEnd.client.person;

import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.services.person.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping(value = "api/person")
public class PersonController
{
    @Autowired
    private PersonService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private Person create(@RequestBody Person resource)
    {
        return service.create(resource);
    }

    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Person> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        Person eventTodelete = service.readById(id);
        if (eventTodelete != null)
        {
            service.delete(eventTodelete);
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Person resource){
        service.update(resource);
    }
}
