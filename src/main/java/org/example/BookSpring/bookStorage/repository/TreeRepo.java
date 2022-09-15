package org.example.BookSpring.bookStorage.repository;

import org.springframework.stereotype.Component;

import java.util.*;

@Component("tree")
//@Primary
public class TreeRepo<E> implements Repository<E> {
    private final Map<Integer, E> mapTreeRepo = new TreeMap<>();

    @Override
    public Optional<E> get(int id) {
        return Optional.ofNullable(mapTreeRepo.get(id));
    }

    @Override
    public List<E> getAll() {
        return new ArrayList<>(mapTreeRepo.values());
    }

    @Override
    public void add(int id, E item) {
        if (item == null)
            throw new NullPointerException();
        mapTreeRepo.put(id, item);
    }

    @Override
    public void remove(int id) {
        mapTreeRepo.remove(id);
    }

    @Override
    public Optional<E> update(int id, E item) {
        return Optional.ofNullable(mapTreeRepo.compute(id, (nId, nItem) -> item));
    }

    @Override
    public void clear() {
        mapTreeRepo.clear();
    }

}
