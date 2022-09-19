package org.example.BookSpring;

import org.example.BookSpring.bookStorage.models.Book;
import org.example.BookSpring.bookStorage.models.User;
import org.example.BookSpring.bookStorage.repository.BookRepository;
import org.example.BookSpring.bookStorage.repository.UserRepository;
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

public class UserRepositoryTest {

    private static UserRepository userRepository;

    private final User user = new User("Carlos Ruiz", LocalDate.parse("2009-03-11"), LocalDate.now());

    @BeforeClass
    public static void setUpUser() {

        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScript("data-users.sql")
                .build();

        userRepository = new UserRepository();
        ReflectionTestUtils.setField(userRepository, "jdbcTemplate", new JdbcTemplate(db));
    }


    @Test
    public void findAllUsers_ReturnList_WhenTheCorrectNumbersUsersStored() {
        int size = userRepository.findAll().size();
        userRepository.save(user);
        List<User> bookList = userRepository.findAll();
        assertEquals(size + 1, bookList.size());
    }

    @Test
    public void findAllUsers_ReturnList_WhenTheWrongNumbersUsersStored() {
        List<User> bookList = userRepository.findAll();
        assertNotEquals(7, bookList.size());
    }

    @Test
    public void showUsers() {
        List<User> bookList = userRepository.findAll();
        System.out.println(bookList);
    }

    @Test
    public void delete_ReturnListOfUsers_AfterDeleteUser() {
        userRepository.delete(1);

        List<User> bookList = userRepository.findAll();
        assertEquals(2, bookList.size());
    }

    @Test
    public void delete_assertTrue_WhenDeleteUserWithIdThatExist() {
        assertEquals(1, userRepository.delete(3));
    }

    @Test
    public void delete_assertFalse_WhenDeleteUserWithIdThatDoesNotExist() {
        assertEquals(0, userRepository.delete(87));
    }

    @Test
    public void get_ReturnOptionalUser_WhenGivenUserIdExists() {
        Optional<User> getUser = userRepository.get(2);
        assertTrue(getUser.isPresent());
    }


    @Test
    public void get_ReturnsOptionalUser_AndCompareWithPreviousValue() {

        Optional<User> nUser = userRepository.get(2);
        assertTrue(nUser.isPresent());
        User user1 = nUser.get();
        assertEquals(user1.getName(), user.getName());
    }

    @Test
    public void get_ReturnsOptionalEmpty_WhenGivenUserIdDoesNotExists() {
        Optional<User> getUser = userRepository.get(19);
        assertFalse(getUser.isPresent());
    }

    @Test
    public void save_ReturnInteger_WhenTheUserWasSaveCorrectly() {

        User userTest = new User("Raul Clark", LocalDate.parse("2009-03-11"), LocalDate.now());

        userRepository.save(userTest);

        assertEquals(1, userRepository.save(userTest));
    }

    @Test
    public void save() {

         User userTest = new User("Raul Clark", LocalDate.parse("2009-03-11"), LocalDate.now());

        userRepository.save(userTest);
        List<User> userList = userRepository.findAll();
        assertEquals(3, userList.size());

    }

    @Test
    public void update_ReturnsIntegerValue_WhenUpdateUserSuccess() {
        user.setName("Carlos Ruiz");

        int checkUpdate = userRepository.update(2, user);
        assertEquals(1, checkUpdate);
    }

    @Test
    public void update_ReturnIntegerValue_WhenDoesNotUpdateUserCorrectly() {

        user.setDateItemTaken(LocalDate.now().plusDays(2));

        int checkUpdate = userRepository.update(10, user);
        assertEquals(0, checkUpdate);
    }


}
