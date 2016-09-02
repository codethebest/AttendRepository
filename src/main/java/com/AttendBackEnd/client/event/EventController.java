package com.AttendBackEnd.client.event;

import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.services.event.impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by Leo on 8/25/2016.
 */
@RestController
@RequestMapping(value ="api/event")
 class EventController {
    @Autowired
    private EventService service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private Event create(@RequestBody Event resource)
    {
        return service.create(resource);
    }

    /***************Find by id******************/

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Event findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Event> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        Event eventTodelete = service.readById(id);
        if (eventTodelete != null)
        {
            service.delete(eventTodelete);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Event resource){
        service.update(resource);
    }
}
