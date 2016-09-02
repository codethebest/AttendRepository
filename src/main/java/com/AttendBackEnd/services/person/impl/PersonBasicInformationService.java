package com.AttendBackEnd.services.person.impl;

import com.AttendBackEnd.domain.person.PersonBasicInformation;
import com.AttendBackEnd.repositories.person.PersonBasicInformationRepository;
import com.AttendBackEnd.services.person.IPersonBasicinformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class PersonBasicInformationService implements IPersonBasicinformationService {
    @Autowired
    private PersonBasicInformationRepository repo;
    @Override
    public PersonBasicInformation create(PersonBasicInformation entity) {

        return repo.save(entity);
    }

    @Override
    public PersonBasicInformation readById(Long id) {

        return repo.findOne(id);
    }

    @Override
    public Set<PersonBasicInformation> readAll()
    {
        Iterable<PersonBasicInformation> personBasicInformations = repo.findAll();
        Set personBasicInfoSet = new HashSet();
        for (PersonBasicInformation personBasicInformation: personBasicInformations)
        {
            personBasicInfoSet.add(personBasicInformation);
        }
        return personBasicInfoSet;
    }

    @Override
    public PersonBasicInformation update(PersonBasicInformation entity) {
        PersonBasicInformation personBasicInformation = readById(entity.getId());
        if (personBasicInformation == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(PersonBasicInformation entity) {
        repo.delete(entity);
    }
}
