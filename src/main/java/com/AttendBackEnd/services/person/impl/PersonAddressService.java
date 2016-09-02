package com.AttendBackEnd.services.person.impl;

import com.AttendBackEnd.domain.person.PersonAddress;
import com.AttendBackEnd.repositories.person.PersonAddressRepository;
import com.AttendBackEnd.services.person.IPersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class PersonAddressService implements IPersonAddressService {
    @Autowired
    private PersonAddressRepository repo;
    @Override
    public PersonAddress create(PersonAddress entity) {
        return repo.save(entity);
    }

    @Override
    public PersonAddress readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<PersonAddress> readAll()
    {
        Iterable<PersonAddress> personAddresses = repo.findAll();
        Set personAddressSet = new HashSet();
        for (PersonAddress personAddress:personAddresses)
        {
            personAddressSet.add(personAddress);
        }
        return personAddressSet;
    }

    @Override
    public PersonAddress update(PersonAddress entity) {
        PersonAddress personAddress = readById(entity.getId());
        if (personAddress == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(PersonAddress entity) {
        repo.delete(entity);
    }
}
