package com.AttendBackEnd.repositories.person;

import com.AttendBackEnd.domain.person.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}
