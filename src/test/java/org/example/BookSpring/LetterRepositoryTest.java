package org.example.BookSpring;


import org.example.BookSpring.bookStorage.models.Letter;

import org.example.BookSpring.bookStorage.repository.LetterRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterRepositoryTest {

    private static LetterRepository daoLetterRepository;

    private final Letter letter = new Letter("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", "Princeton");


    @BeforeClass
    public static void setUp() {

        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScript("data-letters.sql")
                .build();

        daoLetterRepository = new LetterRepository();
        ReflectionTestUtils.setField(daoLetterRepository, "jdbcTemplate", new JdbcTemplate(db));
    }


    @Test
    public void findAllLetters_ReturnList_WhenTheCorrectNumbersLettersStored() {
        int size = daoLetterRepository.findAll().size();
        daoLetterRepository.save(letter);
        List<Letter> letterList = daoLetterRepository.findAll();
        assertEquals(size + 1, letterList.size());
    }

    @Test
    public void findAllLetters_ReturnList_WhenTheWrongNumbersLettersStored() {
        List<Letter> letterList = daoLetterRepository.findAll();
        assertNotEquals(7, letterList.size());
    }

    @Test
    public void showLetters() {
        List<Letter> letterList = daoLetterRepository.findAll();
        System.out.println(letterList);
    }

    @Test
    public void delete_ReturnListOfLetters_AfterDeleteLetter() {
        daoLetterRepository.delete(1);

        List<Letter> letterList = daoLetterRepository.findAll();
        assertEquals(4, letterList.size());
    }

    @Test
    public void delete_assertTrue_WhenDeleteLetterWithIdThatExist() {
        assertEquals(1, daoLetterRepository.delete(3));
    }

    @Test
    public void delete_assertFalse_WhenDeleteLetterWithIdThatDoesNotExist() {
        assertEquals(0, daoLetterRepository.delete(87));
    }

    @Test
    public void get_ReturnOptionalLetter_WhenGivenLetterIdExists() {
        Optional<Letter> getLetter = daoLetterRepository.get(2);
        assertTrue(getLetter.isPresent());
    }


    @Test
    public void get_ReturnsOptionalLetter_AndCompareWithPreviousValue() {

        Optional<Letter> nLetter = daoLetterRepository.get(4);
        assertTrue(nLetter.isPresent());
        Letter book1 = nLetter.get();
        assertEquals(book1.getAuthor(), letter.getAuthor());
    }

    @Test
    public void get_ReturnsOptionalEmpty_WhenGivenLetterIdDoesNotExists() {
        Optional<Letter> getBook = daoLetterRepository.get(19);
        assertFalse(getBook.isPresent());
    }

    @Test
    public void save_ReturnInteger_WhenTheLetterWasSaveCorrectly() {

        Letter letterTest = new Letter("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", "Princeton");
        letterTest.setUserTaken(null);
        daoLetterRepository.save(letterTest);

        assertEquals(1, daoLetterRepository.save(letterTest));
    }

    @Test
    public void save() {

        Letter letterTest = new Letter("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", "Princeton");

        daoLetterRepository.save(letterTest);
        List<Letter> bookList = daoLetterRepository.findAll();
        assertEquals(5, bookList.size());

    }

    @Test
    public void update_ReturnsIntegerValue_WhenUpdateLetterSuccess() {
        letter.setTitle("Build F1 test");

        int checkUpdate = daoLetterRepository.update(2, letter);
        assertEquals(1, checkUpdate);
    }

    @Test
    public void update_ReturnIntegerValue_WhenDoesNotUpdateLetterCorrectly() {

        letter.setDateWrite(LocalDate.now().plusDays(2));

        int checkUpdate = daoLetterRepository.update(10, letter);
        assertEquals(0, checkUpdate);
    }

}
