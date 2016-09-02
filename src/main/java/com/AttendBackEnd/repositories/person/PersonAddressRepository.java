package com.AttendBackEnd.repositories.person;

import com.AttendBackEnd.domain.person.PersonAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface PersonAddressRepository extends CrudRepository<PersonAddress,Long> {
}
