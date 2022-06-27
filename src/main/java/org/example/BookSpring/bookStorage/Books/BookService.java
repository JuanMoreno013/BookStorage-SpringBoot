package org.example.BookSpring.bookStorage.Books;

import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.repository.HashRepo;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final Repository<ItemOp> repository;

    @Autowired
        public BookService(Repository<ItemOp> repository) {
        this.repository = new HashRepo<>();
    }

    public List<ItemOp> getAll() {
        return new ArrayList<>(repository.getAll());
    }

    public Optional<ItemOp> get(int bookId) {
        return repository.get(bookId);
    }

    public Boolean add(ItemOp objItem){
        Objects.requireNonNull(objItem);

        repository.add(objItem.getId(),objItem);
        return true;
    }

    public Boolean delete(int bookId){
        if(bookId<0)
            throw new IllegalArgumentException();

        repository.remove(bookId);
        return true;
    }

    public Optional<ItemOp> update(int bookId, ItemOp bookItem){

        return repository.update(bookId, bookItem);
    }

}
