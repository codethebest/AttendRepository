package com.AttendBackEnd.services.person.impl;

import com.AttendBackEnd.domain.person.Person;
import com.AttendBackEnd.repositories.person.PersonRepository;
import com.AttendBackEnd.services.person.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class PersonService implements IPersonService {
    @Autowired
    private PersonRepository repo;
    @Override
    public Person create(Person entity) {
        return repo.save(entity);
    }

    @Override
    public Person readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Person> readAll() {
        Iterable<Person> persons = repo.findAll();
        Set personSet = new HashSet();
        for (Person person:persons)
        {
            personSet.add(person);
        }
        return personSet;
    }

    @Override
    public Person update(Person entity) {
        Person person = readById(entity.getId());
        if (person == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(Person entity) {
        repo.delete(entity);
    }
}
