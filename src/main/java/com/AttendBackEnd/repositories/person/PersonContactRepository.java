package com.AttendBackEnd.repositories.person;

import com.AttendBackEnd.domain.person.PersonContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface PersonContactRepository extends CrudRepository<PersonContact,Long> {
}
