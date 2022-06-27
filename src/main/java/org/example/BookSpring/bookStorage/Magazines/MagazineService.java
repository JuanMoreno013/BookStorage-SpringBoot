package org.example.BookSpring.bookStorage.Magazines;

import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.repository.HashRepo;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MagazineService {
    private final Repository<ItemOp> repository;

    @Autowired
    public MagazineService(Repository<ItemOp> repository) {
        this.repository = new HashRepo<>();
    }

    public List<ItemOp> getAll() {
        return new ArrayList<>(repository.getAll());
    }

    public Optional<ItemOp> get(int magazineId) {
        return repository.get(magazineId);
    }

    public Boolean add(ItemOp objItem){
        Objects.requireNonNull(objItem);

        repository.add(objItem.getId(),objItem);
        return true;
    }

    public Boolean delete(int magazineId){
        if(magazineId<0)
            throw new IllegalArgumentException();

        repository.remove(magazineId);
        return true;
    }

    public Optional<ItemOp> update(int magazineId, ItemOp magazineItem){

        return repository.update(magazineId, magazineItem);
    }
}
