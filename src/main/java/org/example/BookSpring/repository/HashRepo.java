package org.example.BookSpring.repository;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Component
public class HashRepo<E> implements Repository<E>{
    private final Map< Integer, E> mapRepo = new HashMap<>();


    @Override
    public Optional<E> get(int id) {
        return Optional.ofNullable(mapRepo.get(id));
    }

    @Override
    public List<E> getAll() {
        return new ArrayList<>(mapRepo.values());
    }

    @Override
    public void add( int id, E item) {
        mapRepo.put(id, item);
    }

    @Override
    public void remove(int id) {
        mapRepo.remove(id);
    }

    @Override
    public Optional<E> update(int id, E item) {

//        return Optional.ofNullable(mapRepo.put(id, item));

        return Optional.ofNullable(mapRepo.compute(id, (nId,nItem) -> item));
    }


    //    @Override
//    public void remove(E item) {
//        if (item==null)
//            throw new NullPointerException();
//
//        Consumer<Integer> consumer = mapRepo::remove;
//        mapRepo.entrySet().stream()
//                .filter(entry -> entry.getValue().equals(item))
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .ifPresentOrElse(consumer, () -> { throw new NullPointerException(); });
//
//
//    }
    @Override
    public void clear() {
        mapRepo.clear();
    }

}
