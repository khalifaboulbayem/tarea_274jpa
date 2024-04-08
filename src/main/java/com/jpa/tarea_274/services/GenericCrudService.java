package com.jpa.tarea_274.services;

import java.util.*;

public interface GenericCrudService<T> {
    T add(T newEntity);

    List<T> getAll();

    T getById(Long id);

    T edit(Long id);

    void delete(Long id);
}
