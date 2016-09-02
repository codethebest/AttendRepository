package com.AttendBackEnd.services.user.impl;

import com.AttendBackEnd.domain.user.UserRegistration;
import com.AttendBackEnd.repositories.user.UserRegistrationRepository;
import com.AttendBackEnd.services.user.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class UserRegistrationService implements IUserRegistrationService {
    @Autowired
    private UserRegistrationRepository repo;

    @Override
    public UserRegistration create(UserRegistration entity) {
        return repo.save(entity);
    }

    @Override
    public UserRegistration readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<UserRegistration> readAll() {
        Iterable<UserRegistration> userRegistrations = repo.findAll();
        Set userregiSet = new HashSet();
        for (UserRegistration userRegistration :userRegistrations)
        {
            userregiSet.add(userRegistration);
        }
        return userregiSet;
    }

    @Override
    public UserRegistration update(UserRegistration entity) {
        UserRegistration userRegistration = readById(entity.getId());
        if (userRegistration == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(UserRegistration entity) {
        repo.delete(entity);
    }
}
