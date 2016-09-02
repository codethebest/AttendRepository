package com.AttendBackEnd.client.person;

import com.AttendBackEnd.domain.person.PersonContact;
import com.AttendBackEnd.services.person.impl.PersonContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping (value = "api/personcontact")
public class PersonContactController {
    @Autowired
    private PersonContactService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private PersonContact create(@RequestBody PersonContact resource)
    {
        return service.create(resource);
    }


    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PersonContact findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<PersonContact> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        PersonContact eventContactTodelete = service.readById(id);
        if (eventContactTodelete != null)
        {
            service.delete(eventContactTodelete);
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PersonContact resource){
        service.update(resource);
    }
}