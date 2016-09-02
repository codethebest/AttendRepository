package com.AttendBackEnd.services.user.impl;

import com.AttendBackEnd.domain.user.CommentOnPost;
import com.AttendBackEnd.repositories.user.CommentOnAPostRepository;
import com.AttendBackEnd.services.user.ICommentOnPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class CommentOnPostService implements ICommentOnPostService {
    @Autowired
    private CommentOnAPostRepository repo;
    @Override
    public CommentOnPost create(CommentOnPost entity) {
        return repo.save(entity);
    }

    @Override
    public CommentOnPost readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<CommentOnPost> readAll() {
        Iterable<CommentOnPost> commentOnPosts = repo.findAll();
        Set commentSet = new HashSet();
        for (CommentOnPost commentOnPost:commentOnPosts)
        {
            commentSet.add(commentOnPost);
        }
        return commentSet;
    }

    @Override
    public CommentOnPost update(CommentOnPost entity) {
        CommentOnPost commentOnPost = readById(entity.getId());
        if (commentOnPost == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(CommentOnPost entity) {
            repo.delete(entity);
    }
}
