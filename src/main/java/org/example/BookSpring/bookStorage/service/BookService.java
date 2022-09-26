package org.example.BookSpring.bookStorage.service;

import org.example.BookSpring.bookStorage.converter.BookConverter;
import org.example.BookSpring.bookStorage.dto.BookDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.Book;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
public class BookService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private NotificationCenter notificationCenter;
    @Autowired
    private BookConverter converter;


    public List<BookDto> getAll() {
        Query query = entityManager.createNamedQuery("query_find_all_books", Book.class);
        List<Book> daoBookList = query.getResultList();
        return converter.listEntityToDTO(daoBookList);

    }

    public BookDto get(int bookDtoId) {
        return Optional.of(converter.entityToDTO(entityManager.find(Book.class, bookDtoId)))
                .orElseThrow(() -> new ItemNotFoundException(" NOT found Book with ID : " + bookDtoId));
    }

    public BookDto save(BookDto bookDto) {
        Objects.requireNonNull(bookDto);

        entityManager.persist(converter.dtoToEntity(bookDto));
        notificationCenter.sendNotification("book", "New Book Added !");
        return bookDto;
    }

    public Boolean delete(int bookDtoId) {
        AtomicBoolean checkDelete = new AtomicBoolean(true);

        if (bookDtoId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        Optional<Book> foundBook = Optional.ofNullable(entityManager.find(Book.class, bookDtoId));
        foundBook.ifPresentOrElse((bookId -> entityManager.remove(bookId)), () -> checkDelete.getAndSet(false));

        notificationCenter.sendNotification("book", " Book Removed !");
        return checkDelete.get();
    }

    public Boolean update(int bookDtoId, BookDto bookDto) {

        AtomicBoolean checkUpdate = new AtomicBoolean(true);

        Optional<Book> foundBook = Optional.ofNullable(entityManager.find(Book.class, bookDtoId));
        foundBook.ifPresentOrElse((book -> {
            Book bookEntity = converter.dtoToEntity(bookDto);
            bookEntity.setId(bookDtoId);
            entityManager.merge(bookEntity);
        }), () -> checkUpdate.getAndSet(false));
        return checkUpdate.get();
    }


    public Boolean updateBookTakenBy(int bookDtoId, TakenItemDto takenItemDto) {

        AtomicBoolean checkUpdate = new AtomicBoolean(true);

        Optional<Book> foundBook = Optional.ofNullable(entityManager.find(Book.class, bookDtoId));
        foundBook.ifPresentOrElse(book -> {

            book.setUser_taken(takenItemDto.getUser_taken());
            book.setId(bookDtoId);

            entityManager.merge(book);
        }, () -> checkUpdate.getAndSet(false));
        return checkUpdate.get();
    }


}
