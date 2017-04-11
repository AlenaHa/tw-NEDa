package org.neda.service;

import java.util.List;

/**
 * @param <T> Gives the Object type to
 * @author Elena Hardon
 * @date 4/11/17.
 * <p>
 * This interface defines the basic CRUD methods : create, read, update and delete
 * using : save, findAll, findById, and delete.
 */
public interface CrudService<T> {
    T save(T entity);

    T findById(Long id);

    List<T> findAll();

    void delete(Long id);
}
