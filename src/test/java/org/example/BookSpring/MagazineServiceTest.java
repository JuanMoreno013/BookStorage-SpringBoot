package org.example.BookSpring;

import org.example.BookSpring.bookStorage.converter.MagazineConverter;
import org.example.BookSpring.bookStorage.dto.MagazineDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.models.Magazine;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.service.MagazineService;
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
public class MagazineServiceTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    NotificationCenter notificationCenter;

    @Mock
    private TypedQuery<Magazine> query;

    @Mock
    private MagazineConverter converter;

    @InjectMocks
    private MagazineService magazineService;

    private final String namedQuery = "query_find_all_magazines";


    private final MagazineDto magazineDto = new MagazineDto("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");
    private final Magazine magazineEntity = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");


    @Test
    public void getAll_ReturnListBooks() {

        List<Magazine> magazineList = List.of(magazineEntity, magazineEntity);
        List<MagazineDto> magazineDtoList = List.of(magazineDto, magazineDto);

        Mockito.when(query.getResultList()).thenReturn(magazineList);
        Mockito.when(entityManager.createNamedQuery(namedQuery, Magazine.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(magazineList)).thenReturn(magazineDtoList);

        assertEquals(2, magazineService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(namedQuery, Magazine.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(Collections.emptyList())).thenReturn(Collections.emptyList());

        assertEquals(0, magazineService.getAll().size());
    }

    @Test
    public void get_ReturnBook() {
        int magazineDtoId = 2;

        Mockito.when(entityManager.find(Magazine.class, magazineDtoId)).thenReturn(magazineEntity);
        Mockito.when(converter.entityToDTO(magazineEntity)).thenReturn(magazineDto);

        assertEquals(magazineDto, magazineService.get(magazineDtoId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundMagazine() {
        int magazineDtoId = 9;

        Mockito.when(entityManager.find(Magazine.class, magazineDtoId)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> magazineService.get(magazineDtoId));
    }

    @Test
    public void save_ReturnsMagazine_WHenTryingToSaveCorrectly() {

        entityManager.persist(entityManager);
        assertEquals(magazineDto, magazineService.save(magazineDto));
    }

    @Test
    public void save_ThrowsException_WhenTryingToSaveNullMagazine() {

        assertThrows(NullPointerException.class, () -> magazineService.save(null));
    }

    @Test
    public void delete_ThrowsException_WhenTryingToDeleteMagazineWithNegativeId() {
        int magazineId = -1;

        assertThrows(ItemBadRequestException.class, () -> magazineService.delete(magazineId));
    }

    @Test
    public void delete_ReturnsTrue_WhenDeleteMagazineWithIdThatDoesExist() {
        int magazineDtoId = 2;

        Mockito.when(entityManager.find(Magazine.class, magazineDtoId)).thenReturn(magazineEntity);
        assertEquals(true, magazineService.delete(magazineDtoId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteMagazineWithIdThatDoesNotExist() {
        int magazineDtoId = 99;
        Mockito.when(entityManager.find(Magazine.class, magazineDtoId)).thenReturn(null);

        assertEquals(false, magazineService.delete(magazineDtoId));

    }

    @Test
    public void update_DoesNotThrow_WhenTryingUpdateMagazineWithIdThatDoesNotExist() {
        int magazineId = 99;
        Magazine magazine = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");

        Mockito.when(entityManager.find(Magazine.class, magazineId)).thenReturn(null);
        assertDoesNotThrow(() -> magazineService.update(magazineId, magazineDto));
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateMagazineCorrectly() {
        int magazineId = 2;
        MagazineDto magazineDto2 = new MagazineDto("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");
        Magazine magazineEntity2 = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");

        Mockito.when(entityManager.find(Magazine.class, magazineId)).thenReturn(magazineEntity2);
        Mockito.when(converter.dtoToEntity(magazineDto2)).thenReturn(magazineEntity2);
        Mockito.when(entityManager.merge(magazineEntity2)).thenReturn(magazineEntity2);

        assertEquals(true, magazineService.update(magazineId, magazineDto2));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateMagazineWithNullValue() {

        Mockito.when(entityManager.find(Magazine.class, 54)).thenReturn(null);

        Boolean checkUpdate = magazineService.update(54, null);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void updateTakenBy_ReturnsTrue_WhenTryingUpdateMagazineTakenByWithCorrectUser() {

        TakenItemDto userId = new TakenItemDto(1);

        Mockito.when(entityManager.find(Magazine.class, 2)).thenReturn(magazineEntity);
        Boolean checkUpdate = magazineService.updateMagazineTakenBy(1, userId);

        assertEquals(true, checkUpdate);
    }

    @Test
    public void updateTakenBy_ReturnsTrue_WhenTryingUpdateTakenUserWithNull() {

        TakenItemDto userId = new TakenItemDto(null);

        Mockito.when(entityManager.find(Magazine.class, 2)).thenReturn(magazineEntity);
        Boolean checkUpdate = magazineService.updateMagazineTakenBy(1, userId);

        assertEquals(true, checkUpdate);
    }

    @Test
    public void updateTakenBy_assertTrue_WhenTryingUpdateTakenUserWithValidUser() {

        TakenItemDto userId = new TakenItemDto(99);

        Mockito.when(entityManager.find(Magazine.class, 1)).thenReturn(magazineEntity);
        magazineEntity.setUser_taken(userId.getUser_taken());
        Mockito.when(entityManager.merge(magazineEntity)).thenReturn(magazineEntity);
        magazineService.updateMagazineTakenBy(1, userId);

        assertEquals(userId.getUser_taken(), magazineEntity.getUser_taken() );
    }

}
