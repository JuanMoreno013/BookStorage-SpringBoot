package org.example.BookSpring;

import org.example.BookSpring.bookStorage.converter.LetterConverter;
import org.example.BookSpring.bookStorage.dto.LetterDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.models.Letter;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.service.LetterService;
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
public class LetterServiceTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    NotificationCenter notificationCenter;

    @Mock
    private TypedQuery<Letter> query;

    @Mock
    private LetterConverter converter;

    @InjectMocks
    private LetterService letterService;

    private final String namedQuery = "query_find_all_letters";


    private final LetterDto letterDto = new LetterDto("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", "Princeton");
    private final Letter letterEntity = new Letter("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", "Princeton");


    @Test
    public void getAll_ReturnListLetters() {

        List<Letter> letterList = List.of(letterEntity, letterEntity);
        List<LetterDto> letterDtoList = List.of(letterDto, letterDto);

        Mockito.when(query.getResultList()).thenReturn(letterList);
        Mockito.when(entityManager.createNamedQuery(namedQuery, Letter.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(letterList)).thenReturn(letterDtoList);

        assertEquals(2, letterService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(namedQuery, Letter.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(Collections.emptyList())).thenReturn(Collections.emptyList());

        assertEquals(0, letterService.getAll().size());
    }

    @Test
    public void get_ReturnLetter() {
        int letterDtoId = 2;

        Mockito.when(entityManager.find(Letter.class, letterDtoId)).thenReturn(letterEntity);
        Mockito.when(converter.entityToDTO(letterEntity)).thenReturn(letterDto);

        assertEquals(letterDto, letterService.get(letterDtoId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundLetter() {
        int letterDtoId = 9;

        Mockito.when(entityManager.find(Letter.class, letterDtoId)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> letterService.get(letterDtoId));
    }

    @Test
    public void save_ReturnsLetter_WHenTryingToSaveCorrectly() {

        entityManager.persist(entityManager);
        assertEquals(letterDto, letterService.save(letterDto));
    }

    @Test
    public void save_ThrowsException_WhenTryingToSaveNullLetter() {

        assertThrows(NullPointerException.class, () -> letterService.save(null));
    }

    @Test
    public void delete_ThrowsException_WhenTryingToDeleteLetterWithNegativeId() {
        int bookId = -1;

        assertThrows(ItemBadRequestException.class, () -> letterService.delete(bookId));
    }

    @Test
    public void delete_ReturnsTrue_WhenDeleteLetterWithIdThatDoesExist() {
        int letterDtoId = 2;

        Mockito.when(entityManager.find(Letter.class, letterDtoId)).thenReturn(letterEntity);
        assertEquals(true, letterService.delete(letterDtoId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteLetterWithIdThatDoesNotExist() {
        int letterDtoId = 99;
        Mockito.when(entityManager.find(Letter.class, letterDtoId)).thenReturn(null);

        assertEquals(false, letterService.delete(letterDtoId));

    }

    @Test
    public void update_DoesNotThrow_WhenTryingUpdateLetterWithIdThatDoesNotExist() {
        int bookId = 99;

        Mockito.when(entityManager.find(Letter.class, bookId)).thenReturn(null);
        assertDoesNotThrow(() -> letterService.update(bookId, letterDto));
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateLetterCorrectly() {
        int bookId = 2;
        LetterDto letterDto2 = new LetterDto("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "Master", "Princeton");
        Letter letterEntity2 = new Letter("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "Master", "Princeton");

        Mockito.when(entityManager.find(Letter.class, bookId)).thenReturn(letterEntity2);
        Mockito.when(converter.dtoToEntity(letterDto2)).thenReturn(letterEntity2);
        Mockito.when(entityManager.merge(letterEntity2)).thenReturn(letterEntity2);

        assertEquals(true, letterService.update(bookId, letterDto2));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateLetterWithNullValue() {

        Mockito.when(entityManager.find(Letter.class, 54)).thenReturn(null);

        Boolean checkUpdate = letterService.update(54, null);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void updateTakenBy_ReturnsTrue_WhenTryingUpdateLetterTakenByWithCorrectUser() {

        TakenItemDto userId = new TakenItemDto(1);

        Mockito.when(entityManager.find(Letter.class, 2)).thenReturn(letterEntity);
        Boolean checkUpdate = letterService.updateLetterTakenBy(2, userId);

        assertEquals(true, checkUpdate);
    }

    @Test
    public void updateTakenBy_ReturnsTrue_WhenTryingUpdateTakenUserWithNull() {

        TakenItemDto userId = new TakenItemDto(null);

        Mockito.when(entityManager.find(Letter.class, 2)).thenReturn(letterEntity);
        Boolean checkUpdate = letterService.updateLetterTakenBy(2, userId);

        assertEquals(true, checkUpdate);
    }

    @Test
    public void updateTakenBy_assertTrue_WhenTryingUpdateTakenUserWithInvalidUser() {

        TakenItemDto userId = new TakenItemDto(99);

        Mockito.when(entityManager.find(Letter.class, 1)).thenReturn(letterEntity);
        letterEntity.setUser_taken(userId.getUser_taken());
        Mockito.when(entityManager.merge(letterEntity)).thenReturn(letterEntity);

        letterService.updateLetterTakenBy(1, userId);

        assertEquals(userId.getUser_taken(), letterEntity.getUser_taken() );

    }
}
