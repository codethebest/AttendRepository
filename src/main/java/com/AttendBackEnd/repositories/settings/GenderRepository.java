package com.AttendBackEnd.repositories.settings;

import com.AttendBackEnd.domain.settings.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface GenderRepository extends CrudRepository <Gender,Long> {
}
