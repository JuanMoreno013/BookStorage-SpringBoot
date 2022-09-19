package org.example.BookSpring;

import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.Book;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.repository.BookRepository;
import org.example.BookSpring.bookStorage.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {


    @Mock
    private BookRepository bookRepository;

    @Mock
    NotificationCenter notificationCenter;

    @InjectMocks
    private BookService bookService;


    private final Book bookTest = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");


    @Test
    public void getAll_ReturnListBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(bookTest, bookTest));
        assertEquals(2, bookService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, bookService.getAll().size());
    }


    @Test
    public void get_ReturnOptionalBook() {
        int bookId = 2;

        Mockito.when(bookRepository.get(bookId)).thenReturn(Optional.of(bookTest));
        assertEquals(bookTest, bookService.get(bookId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundBook() {
        int bookId = 9;

        Mockito.when(bookRepository.get(bookId)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> bookService.get(bookId));
    }

    @Test
    public void save_ReturnsTrue_WHenTryingToSaveCorrectly() {

        Mockito.when(bookRepository.save(bookTest)).thenReturn(1);
        Boolean checkSave = bookRepository.save(bookTest) == 1;

        assertEquals(true, checkSave);
    }

    @Test
    public void save_ReturnsFalse_WhenTryingToSaveIncorrectly() {
        Mockito.when(bookRepository.save(bookTest)).thenReturn(0);
        Boolean checkSave = bookRepository.save(bookTest) == 1;

        assertEquals(false, checkSave);
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
    public void delete_ReturnsTRue_WhenDeleteBookWithIdThatDoesExist() {
        int bookId = 2;
        Mockito.when(bookRepository.delete(bookId)).thenReturn(1);
        assertEquals(true, bookService.delete(bookId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteBookWithIdThatDoesNotExist() {
        int bookId = 99;
        Mockito.when(bookRepository.delete(bookId)).thenReturn(0);

        assertEquals(false, bookService.delete(bookId));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateBookWithIdThatDoesNotExist() {
        int bookId = 99;
        Book book = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");

        Mockito.when(bookRepository.update(bookId, book)).thenReturn(0);

        Boolean checkUpdate = bookService.update(bookId, book);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateBookCorrectly() {
        int bookId = 2;
        Book book = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");

        Mockito.when(bookRepository.update(bookId, book)).thenReturn(1);

        Boolean checkUpdate = bookService.update(bookId, book);
        assertEquals(true, checkUpdate);
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateBookWithNullValue() {
        int bookId = 2;

        Mockito.when(bookRepository.update(bookId, null)).thenReturn(0);

        Boolean checkUpdate = bookService.update(bookId, null);
        assertEquals(false, checkUpdate);
    }


}
