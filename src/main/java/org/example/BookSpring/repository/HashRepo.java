package org.example.BookSpring.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("hash")
//@Primary
public class HashRepo<E> implements Repository<E> {
    private final Map<Integer, E> mapRepo = new HashMap<>();


    @Override
    public Optional<E> get(int id) {
        return Optional.ofNullable(mapRepo.get(id));
    }

    @Override
    public List<E> getAll() {
        return new ArrayList<>(mapRepo.values());
    }

    @Override
    public void add(int id, E item) {
        mapRepo.put(id, item);
    }

    @Override
    public void remove(int id) {
        mapRepo.remove(id);
    }

    @Override
    public Optional<E> update(int id, E item) {
        return Optional.ofNullable(mapRepo.compute(id, (nId, nItem) -> item));
    }

    @Override
    public void clear() {
        mapRepo.clear();
    }

}
