package org.example.BookSpring;

import org.example.BookSpring.bookStorage.converter.UserConverter;
import org.example.BookSpring.bookStorage.dto.UserDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.models.User;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.service.UserService;
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
public class UserServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    NotificationCenter notificationCenter;

    @Mock
    private TypedQuery<User> query;

    @Mock
    private UserConverter converter;

    @InjectMocks
    private UserService userService;

    private final String namedQuery = "query_find_all_users";


    private final UserDto userDto = new UserDto("Carlos Ruiz", LocalDate.parse("2009-03-11"), LocalDate.now());
    private final User userEntity = new User("Carlos Ruiz", LocalDate.parse("2009-03-11"), LocalDate.now());


    @Test
    public void getAll_ReturnListUsers() {

        List<User> bookList = List.of(userEntity, userEntity);
        List<UserDto> userDtoList = List.of(userDto, userDto);

        Mockito.when(query.getResultList()).thenReturn(bookList);
        Mockito.when(entityManager.createNamedQuery(namedQuery, User.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(bookList)).thenReturn(userDtoList);

        assertEquals(2, userService.getAll().size());
    }


    @Test
    public void getAll_ReturnEmptyList() {
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createNamedQuery(namedQuery, User.class)).thenReturn(query);
        Mockito.when(converter.listEntityToDTO(Collections.emptyList())).thenReturn(Collections.emptyList());

        assertEquals(0, userService.getAll().size());
    }

    @Test
    public void get_ReturnUser() {
        int userDtoId = 2;

        Mockito.when(entityManager.find(User.class, userDtoId)).thenReturn(userEntity);
        Mockito.when(converter.entityToDTO(userEntity)).thenReturn(userDto);

        assertEquals(userDto, userService.get(userDtoId));
    }

    @Test
    public void get_ThrowException_WhenNotFoundUser() {
        int userDtoId = 9;

        Mockito.when(entityManager.find(User.class, userDtoId)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> userService.get(userDtoId));
    }

    @Test
    public void save_ReturnsUser_WHenTryingToSaveCorrectly() {

        entityManager.persist(entityManager);
        assertEquals(userDto, userService.save(userDto));
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
    public void delete_ReturnsTrue_WhenDeleteUserWithIdThatDoesExist() {
        int userDtoId = 2;

        Mockito.when(entityManager.find(User.class, userDtoId)).thenReturn(userEntity);
        assertEquals(true, userService.delete(userDtoId));
    }

    @Test
    public void delete_ReturnFalse_WhenTryingToDeleteUserWithIdThatDoesNotExist() {
        int userDtoId = 99;
        Mockito.when(entityManager.find(User.class, userDtoId)).thenReturn(null);

        assertEquals(false, userService.delete(userDtoId));

    }

    @Test
    public void update_DoesNotThrow_WhenTryingUpdateUserWithIdThatDoesNotExist() {
        int userId = 99;

        Mockito.when(entityManager.find(User.class, userId)).thenReturn(null);
        assertDoesNotThrow(() -> userService.update(userId, userDto));
    }


    @Test
    public void update_ReturnsTrue_WhenUpdateUserCorrectly() {
        int userId = 2;
        UserDto userDto = new UserDto("Raul Clark", LocalDate.parse("2009-03-11"), LocalDate.now());
        User userEntity = new User("Raul Clark", LocalDate.parse("2009-03-11"), LocalDate.now());

        Mockito.when(entityManager.find(User.class, userId)).thenReturn(userEntity);
        Mockito.when(converter.dtoToEntity(userDto)).thenReturn(userEntity);
        Mockito.when(entityManager.merge(userEntity)).thenReturn(userEntity);

        assertEquals(true, userService.update(userId, userDto));
    }

    @Test
    public void update_ReturnsFalse_WhenTryingUpdateUserWithNullValue() {

        Mockito.when(entityManager.find(User.class, 54)).thenReturn(null);

        Boolean checkUpdate = userService.update(54, null);
        assertEquals(false, checkUpdate);
    }


}
