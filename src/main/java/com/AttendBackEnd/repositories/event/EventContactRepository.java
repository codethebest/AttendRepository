package com.AttendBackEnd.repositories.event;

import com.AttendBackEnd.domain.event.EventContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface EventContactRepository extends CrudRepository<EventContact,Long> {
}
