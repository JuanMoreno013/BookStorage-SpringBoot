package org.example.BookSpring;

import org.example.BookSpring.bookStorage.converter.BookConverter;
import org.example.BookSpring.bookStorage.dto.BookDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.models.Book;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {


    @Mock
    private EntityManager entityManager;

    @Mock
    NotificationCenter notificationCenter;

    @Mock
    private TypedQuery<Book> query;

    @Mock
    private BookConverter converter;

    @InjectMocks
    private BookService bookService;

    private final String namedQuery = "query_find_all_books";


    private final BookDto bookDto = new BookDto("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");
    private final Book bookEntity = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");


    @Test
    public void getAll_ReturnListBooks() {

        List<Book> bookList = List.of(bookEntity, bookEntity);
        List<BookDto> bookDtoList = List.of(bookDto, bookDto);

        Mockito.when(query.getResultList()).thenReturn(bookList);
        Mockito.when(entityManager.createNamedQuery(namedQuery, Book.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(bookList)).thenReturn(bookDtoList);

        assertEquals(2, bookService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(namedQuery, Book.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(Collections.emptyList())).thenReturn(Collections.emptyList());

        assertEquals(0, bookService.getAll().size());
    }

    @Test
    public void get_ReturnBook() {
        int bookDtoId = 2;

        Mockito.when(entityManager.find(Book.class, bookDtoId)).thenReturn(bookEntity);
        Mockito.when(converter.entityToDTO(bookEntity)).thenReturn(bookDto);

        assertEquals(bookDto, bookService.get(bookDtoId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundBook() {
        int bookDtoId = 9;

        Mockito.when(entityManager.find(Book.class, bookDtoId)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> bookService.get(bookDtoId));
    }

    @Test
    public void save_ReturnsBook_WHenTryingToSaveCorrectly() {

        entityManager.persist(entityManager);
        assertEquals(bookDto, bookService.save(bookDto));
    }

    @Test
    public void save_ThrowsException_WhenTryingToSaveNullBook() {

        assertThrows(NullPointerException.class, () -> bookService.save(null));
    }

    @Test
    public void delete_ThrowsException_WhenTryingToDeleteBookWithNegativeId() {
        int bookId = -1;

        assertThrows(ItemBadRequestException.class, () -> bookService.delete(bookId));
    }

    @Test
    public void delete_ReturnsTrue_WhenDeleteBookWithIdThatDoesExist() {
        int bookDtoId = 2;

        Mockito.when(entityManager.find(Book.class, bookDtoId)).thenReturn(bookEntity);
        assertEquals(true, bookService.delete(bookDtoId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteBookWithIdThatDoesNotExist() {
        int bookDtoId = 99;
        Mockito.when(entityManager.find(Book.class, bookDtoId)).thenReturn(null);

        assertEquals(false, bookService.delete(bookDtoId));

    }

    @Test
    public void update_DoesNotThrow_WhenTryingUpdateBookWithIdThatDoesNotExist() {
        int bookId = 99;
        Book book = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");

        Mockito.when(entityManager.find(Book.class, bookId)).thenReturn(null);
        assertDoesNotThrow(() -> bookService.update(bookId, bookDto));
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateBookCorrectly() {
        int bookId = 2;
        BookDto book = new BookDto("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");
        Book bookE = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");

        Mockito.when(entityManager.find(Book.class, bookId)).thenReturn(bookE);
        Mockito.when(converter.dtoToEntity(book)).thenReturn(bookE);
        Mockito.when(entityManager.merge(bookE)).thenReturn(bookE);

        assertEquals(true, bookService.update(bookId, book));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateBookWithNullValue() {

        Mockito.when(entityManager.find(Book.class, 54)).thenReturn(null);

        Boolean checkUpdate = bookService.update(54, null);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void updateTakenBy_ReturnsTrue_WhenTryingUpdateBookTakenByWithCorrectUser() {

        TakenItemDto userId = new TakenItemDto(1);

        Mockito.when(entityManager.find(Book.class, 2)).thenReturn(bookEntity);
        Boolean checkUpdate = bookService.updateBookTakenBy(1, userId);

        assertEquals(true, checkUpdate);
    }

    @Test
    public void updateTakenBy_ReturnsTrue_WhenTryingUpdateTakenUserWithNull() {

        TakenItemDto userId = new TakenItemDto(null);

        Mockito.when(entityManager.find(Book.class, 2)).thenReturn(bookEntity);
        Boolean checkUpdate = bookService.updateBookTakenBy(1, userId);

        assertEquals(true, checkUpdate);
    }

    @Test
    public void updateTakenBy_ThrowsException_WhenTryingUpdateTakenUserWithInvalidUser() {

        TakenItemDto userId = new TakenItemDto(99);

        Mockito.when(entityManager.find(Book.class, 1)).thenReturn(bookEntity);
        bookEntity.setUser_taken(userId.getUser_taken());
        Mockito.when(entityManager.merge(bookEntity)).thenReturn(bookEntity);
        bookService.updateBookTakenBy(1, userId);

        assertEquals(userId.getUser_taken(), bookEntity.getUser_taken());
    }


}
