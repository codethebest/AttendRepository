package com.AttendBackEnd.client.person;

import com.AttendBackEnd.domain.person.PersonBasicInformation;
import com.AttendBackEnd.services.person.impl.PersonBasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping (value = "api/personbasicinformation")
public class PersonBasicInformationController {
    @Autowired
    private PersonBasicInformationService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private PersonBasicInformation create(@RequestBody PersonBasicInformation resource)
    {
        return service.create(resource);
    }
    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PersonBasicInformation findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<PersonBasicInformation> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        PersonBasicInformation eventContactTodelete = service.readById(id);
        if (eventContactTodelete != null)
        {
            service.delete(eventContactTodelete);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PersonBasicInformation resource){
        service.update(resource);
    }
}