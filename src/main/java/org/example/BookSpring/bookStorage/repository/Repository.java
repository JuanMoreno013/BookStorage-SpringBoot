package org.example.BookSpring.bookStorage.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E> {
    Optional<E> get(int key);

    List<E> getAll();

    void add(int id, E item);

    void remove(int id);

    Optional<E> update(int id, E item);

    void clear();

}
