package org.example.BookSpring;

import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.User;
import org.example.BookSpring.bookStorage.repository.UserRepository;
import org.example.BookSpring.bookStorage.service.UserService;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;

    private final User userTest = new User("Carlos Ruiz", LocalDate.parse("2009-03-11"), LocalDate.now());


    @Test
    public void getAll_ReturnListUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(userTest, userTest));
        assertEquals(2, userService.getAllUsers().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, userService.getAllUsers().size());
    }


    @Test
    public void get_ReturnOptionalUser() {
        int userId = 2;

        Mockito.when(userRepository.get(userId)).thenReturn(Optional.of(userTest));
        assertEquals(userTest, userService.get(userId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundUser() {
        int userId = 9;

        Mockito.when(userRepository.get(userId)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> userService.get(userId));
    }

    @Test
    public void save_ReturnsTrue_WHenTryingToSaveCorrectly() {

        Mockito.when(userRepository.save(userTest)).thenReturn(1);
        Boolean checkSave = userRepository.save(userTest) == 1;

        assertEquals(true, checkSave);
    }

    @Test
    public void save_ReturnsFalse_WhenTryingToSaveIncorrectly() {
        Mockito.when(userRepository.save(userTest)).thenReturn(0);
        Boolean checkSave = userRepository.save(userTest) == 1;

        assertEquals(false, checkSave);
    }

    @Test
    public void save_ThrowsException_WhenTryingToSaveNullUser() {

        assertThrows(NullPointerException.class, () -> userService.save(null));
    }

    @Test
    public void delete_ThrowsException_WhenTryingToDeleteUserWithNegativeId() {
        int userId = -1;

        assertThrows(ItemBadRequestException.class, () -> userService.delete(userId));
    }

    @Test
    public void delete_ReturnsTRue_WhenDeleteUserWithIdThatDoesExist() {
        int userId = 2;
        Mockito.when(userRepository.delete(userId)).thenReturn(1);
        assertEquals(true, userService.delete(userId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteUserWithIdThatDoesNotExist() {
        int userId = 99;
        Mockito.when(userRepository.delete(userId)).thenReturn(0);

        assertEquals(false, userService.delete(userId));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateUserWithIdThatDoesNotExist() {
        int userId = 99;
        User user = new User("Raul Clark", LocalDate.parse("2009-03-11"), LocalDate.now());

        Mockito.when(userRepository.update(userId, user)).thenReturn(0);

        Boolean checkUpdate = userService.update(userId, user);
        assertEquals(false, checkUpdate);
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateUserCorrectly() {
        int userId = 2;
        User user = new User("Raul Clark", LocalDate.parse("2009-03-11"), LocalDate.now());

        Mockito.when(userRepository.update(userId, user)).thenReturn(1);

        Boolean checkUpdate = userService.update(userId, user);
        assertEquals(true, checkUpdate);
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateUserWithNullValue() {
        int userId = 2;

        Mockito.when(userRepository.update(userId, null)).thenReturn(0);

        Boolean checkUpdate = userService.update(userId, null);
        assertEquals(false, checkUpdate);
    }
}
