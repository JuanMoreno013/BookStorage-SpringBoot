package org.example.BookSpring.bookStorage.Books;

import org.example.BookSpring.bookStorage.Vexceptions.ItemBadRequestException;
import org.example.BookSpring.bookStorage.Vexceptions.ItemNotFoundException;
import org.example.BookSpring.bookStorage.Notification.NotificationCenter;
import org.example.BookSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository repository;
    private final NotificationCenter notificationCenter;

    @Autowired
    public BookService(BookRepository repository, NotificationCenter notificationCenter) {
        this.repository = repository;
        this.notificationCenter = notificationCenter;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book get(int bookId) {
        return repository.get(bookId)
                .orElseThrow(() -> new ItemNotFoundException(" NOT found book with ID : " + bookId));
    }

    public Boolean save(Book book) {
        Objects.requireNonNull(book);


        notificationCenter.sendNotification("book", "New Book Added !");
        return repository.save(book) ==1;
    }

    public Boolean delete(int bookId) {
        if (bookId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        notificationCenter.sendNotification("book", "Book Removed !");
        return repository.delete(bookId) == 1;

    }

    public Boolean update(int bookId, Book bookItem) {

        return repository.update(bookId, bookItem) == 1;
    }

}
