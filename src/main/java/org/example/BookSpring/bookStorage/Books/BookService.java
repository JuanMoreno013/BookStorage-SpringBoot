package org.example.BookSpring.bookStorage.Books;

import org.example.BookSpring.bookStorage.Vexceptions.ItemBadRequestException;
import org.example.BookSpring.bookStorage.Vexceptions.ItemNotFoundException;
import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Notification.NotificationCenter;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService<T> {
    private final Repository<ItemOp> repository;
    private final NotificationCenter notificationCenter;

    @Autowired
    public BookService(@Qualifier(value = "tree") Repository<ItemOp> repository, NotificationCenter notificationCenter) {
        this.repository = repository;
        this.notificationCenter = notificationCenter;
    }

    public List<Book> getAll() {
        return repository.getAll().
                stream()
                .filter(e -> e instanceof Book)
                .map(item -> (Book) item)
                .collect(Collectors.toList());
    }

    public Optional<Book> get(int bookId) {
        return Optional.ofNullable(repository.get(bookId)
                .map(item -> (Book) item)
                .orElseThrow(() -> new ItemNotFoundException(" NOT found book with ID : " + bookId)));

    }

    public Boolean add(ItemOp objItem) {
        Objects.requireNonNull(objItem);

        notificationCenter.sendNotification("book", "New Book Added !");
        repository.add(objItem.getId(), objItem);
        return true;
    }

    public Boolean delete(int bookId) {
        if (bookId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        boolean CheckBook = repository.get(bookId)
                .filter(item -> item instanceof Book)
                .isPresent();

        if (CheckBook) {
            repository.remove(bookId);
            notificationCenter.sendNotification("book", "Book Removed !");
            return true;
        }

        throw new ItemNotFoundException(" NOT found book with ID " + bookId + " to be removed !");
    }

    public Optional<Book> update(int bookId, ItemOp bookItem) {

        boolean CheckBook = repository.get(bookId)
                .filter(item -> item instanceof Book)
                .isPresent();

        if (!CheckBook) {
            throw new ItemNotFoundException(" NOT found book with ID " + bookId + " to be updated !");
        }
        bookItem.setId(bookId);
        return repository.update(bookId, bookItem)
                .map(e -> (Book) e);
    }

}
