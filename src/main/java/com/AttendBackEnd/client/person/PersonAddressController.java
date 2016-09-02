package com.AttendBackEnd.client.person;

import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.domain.person.PersonAddress;
import com.AttendBackEnd.services.person.impl.PersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping(value = "api/personaddress")
public class PersonAddressController {
    @Autowired
    private PersonAddressService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private PersonAddress create(@RequestBody PersonAddress resource)
    {
        return service.create(resource);
    }


    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PersonAddress findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<PersonAddress> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        PersonAddress eventAddressTodelete = service.readById(id);
        if (eventAddressTodelete != null)
        {
            service.delete(eventAddressTodelete);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PersonAddress resource){
        service.update(resource);
    }
}