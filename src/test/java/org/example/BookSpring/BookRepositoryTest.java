package org.example.BookSpring;

import org.example.BookSpring.bookStorage.models.Book;
import org.example.BookSpring.bookStorage.repository.BookRepository;

import org.junit.BeforeClass;

import org.junit.Test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest {


    private static BookRepository daoBookRepository;

    private final Book book = new Book("Build F1 CAR4", "Andrew Newey", 578, LocalDate.now(), "Mechanic", "89P-92-NJ-1", "New", "Oxford");


    @BeforeClass
    public static void setUp() {

        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScript("data-books.sql")
                .build();

        daoBookRepository = new BookRepository();
        ReflectionTestUtils.setField(daoBookRepository, "jdbcTemplate", new JdbcTemplate(db));
    }


    @Test
//    @DisplayName("Find All books, shows true , when return all the books stored")
    public void findAllBooks_ReturnList_WhenTheCorrectNumbersBooksStored() {
        int size = daoBookRepository.findAll().size();
        daoBookRepository.save(book);
        List<Book> bookList = daoBookRepository.findAll();
        assertEquals(size + 1, bookList.size());
    }

    @Test
    public void findAllBooks_ReturnList_WhenTheWrongNumbersBooksStored() {
        List<Book> bookList = daoBookRepository.findAll();
        assertNotEquals(7, bookList.size());
    }

    @Test
    public void showBooks() {
        List<Book> bookList = daoBookRepository.findAll();
        System.out.println(bookList);
    }

    @Test
    public void delete_ReturnListOfBooks_AfterDeleteBook() {
        daoBookRepository.delete(1);

        List<Book> bookList = daoBookRepository.findAll();
        assertEquals(4, bookList.size());
    }

    @Test
    public void delete_assertTrue_WhenDeleteBookWithIdThatExist() {
        assertEquals(1, daoBookRepository.delete(3));
    }

    @Test
    public void delete_assertFalse_WhenDeleteBookWithIdThatDoesNotExist() {
        assertEquals(0, daoBookRepository.delete(87));
    }

    @Test
    public void get_ReturnOptionalBook_WhenGivenBookIdExists() {
        Optional<Book> getBook = daoBookRepository.get(2);
        assertTrue(getBook.isPresent());
    }


    @Test
    public void get_ReturnsOptionalBook_AndCompareWithPreviousValue() {

        Optional<Book> nBook = daoBookRepository.get(4);
        assertTrue(nBook.isPresent());
        Book book1 = nBook.get();
        assertEquals(book1.getAuthor(), book.getAuthor());
    }

    @Test
    public void get_ReturnsOptionalEmpty_WhenGivenBookIdDoesNotExists() {
        Optional<Book> getBook = daoBookRepository.get(19);
        assertFalse(getBook.isPresent());
    }

    @Test
    public void save_ReturnInteger_WhenTheBookWasSaveCorrectly() {

        Book bookTest = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");
        bookTest.setUser_taken(null);
        daoBookRepository.save(bookTest);

        assertEquals(1, daoBookRepository.save(bookTest));
    }

    @Test
    public void save() {

        Book bookTest = new Book("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "910-92-00-1", "Master", "New", "Princeton");

        daoBookRepository.save(bookTest);
        List<Book> bookList = daoBookRepository.findAll();
        assertEquals(4, bookList.size());

    }

    @Test
    public void update_ReturnsIntegerValue_WhenUpdateBookSuccess() {
        book.setTitle("Build F1 test");

        int checkUpdate = daoBookRepository.update(2, book);
        assertEquals(1, checkUpdate);
    }

    @Test
    public void update_ReturnIntegerValue_WhenDoesNotUpdateBookCorrectly() {

        book.setDate_write(LocalDate.now().plusDays(2));

        int checkUpdate = daoBookRepository.update(10, book);
        assertEquals(0, checkUpdate);
    }


}