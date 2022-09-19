package org.example.BookSpring;

import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.Magazine;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;

import org.example.BookSpring.bookStorage.repository.MagazineRepository;
import org.example.BookSpring.bookStorage.service.MagazineService;
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
public class MagazineServiceTest {

    @Mock
    private MagazineRepository magazineRepository;

    @Mock
    NotificationCenter notificationCenter;

    @InjectMocks
    private MagazineService magazineService;


    private final Magazine magazine = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");


    @Test
    public void getAll_ReturnListMagazines() {
        Mockito.when(magazineRepository.findAll()).thenReturn(List.of(magazine, magazine));
        assertEquals(2, magazineService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(magazineRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, magazineService.getAll().size());
    }


    @Test
    public void get_ReturnOptionalMagazine() {
        int magazineId = 2;

        Mockito.when(magazineRepository.get(magazineId)).thenReturn(Optional.of(magazine));
        assertEquals(magazine, magazineService.get(magazineId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundMagazine() {
        int magazineId = 9;

        Mockito.when(magazineRepository.get(magazineId)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> magazineService.get(magazineId));
    }

    @Test
    public void save_ReturnsTrue_WHenTryingToSaveCorrectly() {

        Mockito.when(magazineRepository.save(magazine)).thenReturn(1);
        Boolean checkSave = magazineRepository.save(magazine) == 1;

        assertEquals(true, checkSave);
    }

    @Test
    public void save_ReturnsFalse_WhenTryingToSaveIncorrectly() {
        Mockito.when(magazineRepository.save(magazine)).thenReturn(0);
        Boolean checkSave = magazineRepository.save(magazine) == 1;

        assertEquals(false, checkSave);
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
    public void delete_ReturnsTRue_WhenDeleteMagazineWithIdThatDoesExist() {
        int magazineId = 2;
        Mockito.when(magazineRepository.delete(magazineId)).thenReturn(1);
        assertEquals(true, magazineService.delete(magazineId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteMagazineWithIdThatDoesNotExist() {
        int magazineId = 99;
        Mockito.when(magazineRepository.delete(magazineId)).thenReturn(0);

        assertEquals(false, magazineService.delete(magazineId));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateMagazineWithIdThatDoesNotExist() {
        int magazineId = 99;
        Magazine magazine = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");
        Mockito.when(magazineRepository.update(magazineId, magazine)).thenReturn(0);

        Boolean checkUpdate = magazineService.update(magazineId, magazine);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateMagazineCorrectly() {
        int magazineId = 2;
        Magazine magazine = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");
        Mockito.when(magazineRepository.update(magazineId, magazine)).thenReturn(1);

        Boolean checkUpdate = magazineService.update(magazineId, magazine);
        assertEquals(true, checkUpdate);
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateMagazineWithNullValue() {
        int magazineId = 2;

        Mockito.when(magazineRepository.update(magazineId, null)).thenReturn(0);

        Boolean checkUpdate = magazineService.update(magazineId, null);
        assertEquals(false, checkUpdate);
    }

}
