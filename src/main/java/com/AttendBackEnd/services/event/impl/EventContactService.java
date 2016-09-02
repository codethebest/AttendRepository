package com.AttendBackEnd.services.event.impl;

import com.AttendBackEnd.domain.event.EventContact;
import com.AttendBackEnd.repositories.event.EventContactRepository;
import com.AttendBackEnd.services.event.IEventContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class EventContactService implements IEventContactService{
    @Autowired
    private EventContactRepository repo;
    @Override
    public EventContact create(EventContact entity) {
        return repo.save(entity);
    }

    @Override
    public EventContact readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<EventContact> readAll() {
        Iterable<EventContact> eventContacts = repo.findAll();
        Set eventContactSet = new HashSet();
        for (EventContact eventContact : eventContacts)
        {
            eventContactSet.add(eventContact);
        }
        return eventContactSet;
    }

    @Override
    public EventContact update(EventContact entity) {
        EventContact eventContact = readById(entity.getId());
        if (eventContact == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(EventContact entity) {
        repo.delete(entity);
    }
}
