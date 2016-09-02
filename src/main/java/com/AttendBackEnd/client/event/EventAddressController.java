package com.AttendBackEnd.client.event;

import com.AttendBackEnd.domain.event.EventAddress;
import com.AttendBackEnd.services.event.impl.EventAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping(value = "/api/eventaddress")
public class EventAddressController {
    @Autowired
    private EventAddressService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private EventAddress create(@RequestBody EventAddress resource)

    {
        return service.create(resource);
    }


    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EventAddress findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<EventAddress> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        EventAddress eventAddressTodelete = service.readById(id);
        if (eventAddressTodelete != null)
        {
            service.delete(eventAddressTodelete);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody EventAddress resource){
        service.update(resource);
    }
}