package ru.edu.repository;

import java.util.List;

public interface GenericRepository <T,ID> {

    T getById(ID id);

    T add(T item);

    void delete(ID id);

    T update(T id);

    List<T> getAll();

}
