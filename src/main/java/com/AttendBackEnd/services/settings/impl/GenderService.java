package com.AttendBackEnd.services.settings.impl;

import com.AttendBackEnd.domain.settings.Gender;
import com.AttendBackEnd.repositories.settings.GenderRepository;
import com.AttendBackEnd.services.settings.IGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class GenderService implements IGenderService {
    @Autowired
    private GenderRepository repo;
    @Override
    public Gender create(Gender entity) {
        return repo.save(entity);
    }

    @Override
    public Gender readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Gender> readAll() {
        Iterable<Gender> genders = repo.findAll();
        Set genderSet = new HashSet();
        for (Gender gender:genders)
        {
            genderSet.add(gender);
        }
        return genderSet;
    }

    @Override
    public Gender update(Gender entity) {
        Gender gender = readById(entity.getId());
        if (gender == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(Gender entity) {
        repo.delete(entity);
    }
}
