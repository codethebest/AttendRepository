package com.AttendBackEnd.services.event.impl;


import com.AttendBackEnd.domain.event.EventAddress;
import com.AttendBackEnd.repositories.event.EventAddressRepository;
import com.AttendBackEnd.services.event.IEventAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class EventAddressService implements IEventAddressService {
    @Autowired
    private EventAddressRepository eventAddressRepository;

    @Override
    public EventAddress create(EventAddress entity) {
        return eventAddressRepository.save(entity);
    }

    @Override
    public EventAddress readById(Long id) {
        return eventAddressRepository.findOne(id);
    }

    @Override
    public Set<EventAddress> readAll() {
        Iterable<EventAddress> eventAddresses = eventAddressRepository.findAll();
        Set eventaddressset = new HashSet();
        for (EventAddress eventAddress : eventAddresses)
        {
            eventaddressset.add(eventAddress);
        }
        return eventaddressset;
    }

    @Override
    public EventAddress update(EventAddress entity) {
        EventAddress eventAddress = readById(entity.getId());
        if (eventAddress == null) {
            return null;
        }
        return eventAddressRepository.save(entity);
    }

    @Override
    public void delete(EventAddress entity)
    {
        eventAddressRepository.delete(entity);
    }
}
