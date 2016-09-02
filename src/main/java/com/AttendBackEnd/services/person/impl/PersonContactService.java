package com.AttendBackEnd.services.person.impl;

import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.domain.person.PersonContact;
import com.AttendBackEnd.repositories.person.PersonContactRepository;
import com.AttendBackEnd.services.person.IPersonContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class PersonContactService implements IPersonContactService{
    @Autowired
    private PersonContactRepository repo;
    @Override
    public PersonContact create(PersonContact entity) {
        return repo.save(entity);
    }

    @Override
    public PersonContact readById(Long id) {

        return repo.findOne(id);
    }

    @Override
    public Set<PersonContact> readAll() {
        Iterable<PersonContact> personContacts = repo.findAll();
        Set personSet = new HashSet();
        for (PersonContact personContact :personContacts)
        {
            personSet.add(personContact);
        }
        return personSet;
    }

    @Override
    public PersonContact update(PersonContact entity) {
        PersonContact personContact = readById(entity.getId());
        if (personContact == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(PersonContact entity) {
        repo.delete(entity);
    }
}
