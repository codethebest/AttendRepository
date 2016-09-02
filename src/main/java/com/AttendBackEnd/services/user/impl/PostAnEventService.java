package com.AttendBackEnd.services.user.impl;

import com.AttendBackEnd.domain.user.PostAnEvent;
import com.AttendBackEnd.repositories.user.PostAnEventRepository;
import com.AttendBackEnd.services.user.IPostAEventService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
@Service
public class PostAnEventService implements IPostAEventService {
    @Autowired
    private PostAnEventRepository repo;
    @Override
    public PostAnEvent create(PostAnEvent entity) {
        return repo.save(entity);
    }

    @Override
    public PostAnEvent readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<PostAnEvent> readAll() {
        Iterable<PostAnEvent> postAnEvents = repo.findAll();
        Set postSet = new HashSet();
        for(PostAnEvent postAnEvent:postAnEvents)
        {
            postSet.add(postAnEvent);
        }
        return postSet;
    }

    @Override
    public PostAnEvent update(PostAnEvent entity) {
        PostAnEvent postAnEvent= readById(entity.getId());
        if (postAnEvent == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(PostAnEvent entity) {
        repo.delete(entity);
    }
}
