package org.example.BookSpring.bookStorage.Books;

import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Letters.Letter;
import org.example.BookSpring.bookStorage.Notification.Observer;
import org.example.BookSpring.bookStorage.Notification.SendMessage;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService<T> extends SendMessage<T>{
    private final Repository<ItemOp> repository;
    //    SendMessage bookMessage = new SendMessage();

    @Autowired
        public BookService(Repository<ItemOp> repository, Observer<T> observer) {
        addObserver(observer);
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.getAll().
                     stream()
                     .filter(e-> e instanceof Book)
                     .map(item -> (Book) item)
                     .collect(Collectors.toList());
    }

    public Optional<Book> get(int bookId) {
        return repository.get(bookId)
                .map(item -> (Book) item);
    }

    public Boolean add(ItemOp objItem){
        Objects.requireNonNull(objItem);

        setMessage("New Book Added");
        notifyAllObservers();

        repository.add(objItem.getId(),objItem);
        return true;
    }

    public Boolean delete(int bookId){
        if(bookId<0)
            throw new IllegalArgumentException();

        boolean CheckBook = repository.get(bookId)
                .filter(item -> item instanceof Book)
                .isPresent();

        if (CheckBook) {
            repository.remove(bookId);
            setMessage("Book Removed! ");
            notifyAllObservers();
            return true;
        }

        return false;
    }

    public Optional<Book> update(int bookId, ItemOp bookItem){

        boolean CheckBook = repository.get(bookId)
                .filter(item -> item instanceof Book)
                .isPresent();

        if (!CheckBook) {
            return Optional.empty();
        }
        bookItem.setId(bookId);
        return repository.update(bookId, bookItem)
                .map(e -> (Book) e);

    }

}
