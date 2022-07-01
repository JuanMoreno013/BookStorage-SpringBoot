package org.example.BookSpring.bookStorage.Letters;

import org.example.BookSpring.bookStorage.Books.Book;
import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Notification.Observer;
import org.example.BookSpring.bookStorage.Notification.SendMessage;
import org.example.BookSpring.repository.HashRepo;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LetterService<T> extends SendMessage<T> {
    private final Repository<ItemOp> repository;
    private final Observer<T> observer2;

    @Autowired
    public LetterService(Repository<ItemOp> repository, Observer<T> observer) {
        this.observer2 = observer;
        addObserver(this.observer2);
        this.repository = repository;
    }

    public List<ItemOp> getAll() {
        return repository.getAll()
                .stream()
                .filter(e-> e instanceof Letter)
                .collect(Collectors.toList());
    }

    public Optional<ItemOp> get(int letterId) {
        return repository.get(letterId);
    }

    public Boolean add(ItemOp objItem){
        Objects.requireNonNull(objItem);

        setMessage("New Letter Added! ");
        notifyAllObservers();

        repository.add(objItem.getId(),objItem);
        return true;
    }

    public Boolean delete(int letterId){
        if(letterId<0)
            throw new IllegalArgumentException();

        boolean CheckLetter = repository.get(letterId)
                .filter(item -> item instanceof Letter)
                .isPresent();

        if (CheckLetter) {
            repository.remove(letterId);
            setMessage("Letter Removed! ");
            notifyAllObservers();
            return true;
        }

        return false;
    }

    public Optional<ItemOp> update(int letterId, ItemOp letterItem){

        boolean CheckLetter = repository.get(letterId)
                .filter(item -> item instanceof Letter)
                .isPresent();

        if (!CheckLetter) {
            return Optional.empty();
        }

        letterItem.setId(letterId);
        return repository.update(letterId, letterItem);
    }

}
