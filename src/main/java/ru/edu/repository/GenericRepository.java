package ru.edu.repository;

import java.util.List;

public interface GenericRepository <T,ID> {

    T getById(ID id) throws Exception;

    void add(T item) throws Exception;

    void delete(ID id) throws Exception;

    void update(ID id) throws Exception;

    void save(ID id);

    List<T> getAll() throws Exception;

}
