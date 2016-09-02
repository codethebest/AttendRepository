package com.AttendBackEnd.client.user;

import com.AttendBackEnd.domain.user.UserRegistration;
import com.AttendBackEnd.services.user.impl.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping(value = "api/userregistration")
public class UserRegistrationController {
    @Autowired
    private UserRegistrationService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private UserRegistration create(@RequestBody UserRegistration resource)
    {
        return service.create(resource);
    }


    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserRegistration findbyId(@PathVariable Long id) {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<UserRegistration> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        UserRegistration eventContactTodelete = service.readById(id);
        if (eventContactTodelete != null)
        {
            service.delete(eventContactTodelete);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UserRegistration resource){
        service.update(resource);
    }
}