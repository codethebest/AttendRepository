package com.AttendBackEnd.repositories.user;

import com.AttendBackEnd.domain.user.UserRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface UserRegistrationRepository extends CrudRepository <UserRegistration,Long> {
}
