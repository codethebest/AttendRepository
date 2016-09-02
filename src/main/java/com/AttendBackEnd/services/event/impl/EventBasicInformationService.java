package com.AttendBackEnd.services.event.impl;

import com.AttendBackEnd.domain.event.EventBasicInformation;
import com.AttendBackEnd.repositories.event.EventBasicinformationRepository;
import com.AttendBackEnd.services.event.IEventBasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class EventBasicInformationService implements IEventBasicInformationService {

    @Autowired
    private EventBasicinformationRepository eventBasicinformationRepository;

    @Override
    public EventBasicInformation create(EventBasicInformation entity)
    {
        return eventBasicinformationRepository.save(entity);
    }

    @Override
    public EventBasicInformation readById(Long id)
    {
        return eventBasicinformationRepository.findOne(id);
    }

    @Override
    public Set<EventBasicInformation> readAll() {
        Iterable <EventBasicInformation> eventBasicInformations= eventBasicinformationRepository.findAll();
        Set eventBInfoset = new HashSet();
        for (EventBasicInformation eventBasicInformation : eventBasicInformations)
        {
            eventBInfoset.add(eventBasicInformation);
        }
        return eventBInfoset;
    }

    @Override
    public EventBasicInformation update(EventBasicInformation entity) {
        EventBasicInformation eventBasicInformation = readById(entity.getId());
        if (eventBasicInformation == null)
        {
            return null;
        }
        return eventBasicinformationRepository.save(entity);
    }

    @Override
    public void delete(EventBasicInformation entity) {
        eventBasicinformationRepository.delete(entity);
    }
}
