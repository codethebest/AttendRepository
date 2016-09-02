package com.AttendBackEnd.services;

import java.util.Set;

/**
 * Created by Leo on 8/14/2016.
 */
public interface Services <E,ID> {
    E create(E entity);

    E readById(ID id);

    Set<E> readAll();

    E update(E entity);

    void delete(E entity);
}
