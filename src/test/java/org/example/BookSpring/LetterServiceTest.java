package org.example.BookSpring;

import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.Letter;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.repository.LetterRepository;

import org.example.BookSpring.bookStorage.service.LetterService;
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
public class LetterServiceTest {

    @Mock
    private LetterRepository letterRepository;

    @Mock
    NotificationCenter notificationCenter;

    @InjectMocks
    private LetterService letterService;


    private final Letter letterTest = new Letter("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", "Princeton");


    @Test
    public void getAll_ReturnListLetters() {
        Mockito.when(letterRepository.findAll()).thenReturn(List.of(letterTest, letterTest));
        assertEquals(2, letterService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(letterRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, letterService.getAll().size());
    }


    @Test
    public void get_ReturnOptionalLetter() {
        int letterId = 2;

        Mockito.when(letterRepository.get(letterId)).thenReturn(Optional.of(letterTest));
        assertEquals(letterTest, letterService.get(letterId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundLetter() {
        int letterId = 9;

        Mockito.when(letterRepository.get(letterId)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> letterService.get(letterId));
    }

    @Test
    public void save_ReturnsTrue_WHenTryingToSaveCorrectly() {

        Mockito.when(letterRepository.save(letterTest)).thenReturn(1);
        Boolean checkSave = letterRepository.save(letterTest) == 1;

        assertEquals(true, checkSave);
    }

    @Test
    public void save_ReturnsFalse_WhenTryingToSaveIncorrectly() {
        Mockito.when(letterRepository.save(letterTest)).thenReturn(0);
        Boolean checkSave = letterRepository.save(letterTest) == 1;

        assertEquals(false, checkSave);
    }

    @Test
    public void save_ThrowsException_WhenTryingToSaveNullLetter() {

        assertThrows(NullPointerException.class, () -> letterService.save(null));
    }

    @Test
    public void delete_ThrowsException_WhenTryingToDeleteLetterWithNegativeId() {
        int letterId = -1;

        assertThrows(ItemBadRequestException.class, () -> letterService.delete(letterId));
    }

    @Test
    public void delete_ReturnsTRue_WhenDeleteLetterWithIdThatDoesExist() {
        int letterId = 2;
        Mockito.when(letterRepository.delete(letterId)).thenReturn(1);
        assertEquals(true, letterService.delete(letterId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteLetterWithIdThatDoesNotExist() {
        int letterId = 99;
        Mockito.when(letterRepository.delete(letterId)).thenReturn(0);

        assertEquals(false, letterService.delete(letterId));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateLetterWithIdThatDoesNotExist() {
        int letterId = 99;
        Letter letter = new Letter("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "Master", "Princeton");

        Mockito.when(letterRepository.update(letterId, letter)).thenReturn(0);

        Boolean checkUpdate = letterService.update(letterId, letter);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateLetterCorrectly() {
        int letterId = 2;
        Letter letter = new Letter("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "Master", "Princeton");

        Mockito.when(letterRepository.update(letterId, letter)).thenReturn(1);

        Boolean checkUpdate = letterService.update(letterId, letter);
        assertEquals(true, checkUpdate);
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateLetterWithNullValue() {
        int letterId = 2;

        Mockito.when(letterRepository.update(letterId, null)).thenReturn(0);

        Boolean checkUpdate = letterService.update(letterId, null);
        assertEquals(false, checkUpdate);
    }

}
