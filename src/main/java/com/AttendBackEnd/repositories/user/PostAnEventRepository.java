package com.AttendBackEnd.repositories.user;

import com.AttendBackEnd.domain.user.PostAnEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface PostAnEventRepository extends CrudRepository <PostAnEvent ,Long> {
}
