package com.AttendBackEnd.services.event.impl;

import com.AttendBackEnd.domain.event.Event;
import com.AttendBackEnd.repositories.event.EventRepository;
import com.AttendBackEnd.services.event.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class EventService implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event create(Event entity) {
        return eventRepository.save(entity);
    }

    @Override
    public Event readById(Long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Set<Event> readAll()
    {
        Iterable<Event> events= eventRepository.findAll();
        Set eventsSet = new HashSet();
        for (Event event:events)
        {
            eventsSet.add(event);
        }
        return eventsSet;
    }

    @Override
    public Event update(Event entity)
    {
        return eventRepository.save(entity);
    }

    @Override
    public void delete(Event entity) {
        eventRepository.delete(entity);
    }
}
