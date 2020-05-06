package com.dilipkumarg.fk.newsfeed.repo;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T save(ID id, T entity);

    T update(ID id, T entity);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    boolean remove(ID id);
}
