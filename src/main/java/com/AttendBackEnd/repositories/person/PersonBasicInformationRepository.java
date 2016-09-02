package com.AttendBackEnd.repositories.person;

import com.AttendBackEnd.domain.person.PersonBasicInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface PersonBasicInformationRepository extends CrudRepository<PersonBasicInformation,Long> {
}
