package com.AttendBackEnd.repositories.user;

import org.springframework.data.repository.CrudRepository;
import com.AttendBackEnd.domain.user.CommentOnPost;
import org.springframework.stereotype.Repository;

/**
 * Created by Leo on 8/13/2016.
 */
@Repository
public interface CommentOnAPostRepository extends CrudRepository<CommentOnPost , Long> {
}
