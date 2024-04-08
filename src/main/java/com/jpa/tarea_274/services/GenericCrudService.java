package com.jpa.tarea_274.services;

import java.util.*;

public interface GenericCrudService<T, E> {
    T add(E newEntity);

    List<T> getAll();

    T getById(Long id);

    T edit(Long id, E newEntity);

    void delete(Long id);
}
