package org.example.BookSpring;

import org.example.BookSpring.bookStorage.models.Magazine;
import org.example.BookSpring.bookStorage.repository.MagazineRepository;
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

public class MagazineRepositoryTest {

    private static MagazineRepository daoMagazineRepository;

    private final Magazine magazine = new Magazine("New Topics", "Arnold Lox", 345, LocalDate.now(), "Fashion", 1, "Princeton");

    @BeforeClass
    public static void setUp() {

        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScript("data-magazines.sql")
                .build();

        daoMagazineRepository = new MagazineRepository();
        ReflectionTestUtils.setField(daoMagazineRepository, "jdbcTemplate", new JdbcTemplate(db));
    }


    @Test
//    @DisplayName("Find All books, shows true , when return all the books stored")
    public void findAllBooks_ReturnList_WhenTheCorrectNumbersBooksStored() {
        int size = daoMagazineRepository.findAll().size();
        daoMagazineRepository.save(magazine);
        List<Magazine> magazineList = daoMagazineRepository.findAll();
        assertEquals(size + 1, magazineList.size());
    }

    @Test
    public void findAllBooks_ReturnList_WhenTheWrongNumbersBooksStored() {
        List<Magazine> magazineList = daoMagazineRepository.findAll();
        assertNotEquals(7, magazineList.size());
    }

    @Test
    public void showMagazines() {
        List<Magazine> magazineList = daoMagazineRepository.findAll();
        System.out.println(magazineList);
    }

    @Test
    public void delete_ReturnListOfMagazines_AfterDeleteMagazine() {
        daoMagazineRepository.delete(1);

        List<Magazine> magazineList = daoMagazineRepository.findAll();
        assertEquals(4, magazineList.size());
    }

    @Test
    public void delete_assertTrue_WhenDeleteMagazineWithIdThatExist() {
        assertEquals(1, daoMagazineRepository.delete(3));
    }

    @Test
    public void delete_assertFalse_WhenDeleteMagazineWithIdThatDoesNotExist() {
        assertEquals(0, daoMagazineRepository.delete(87));
    }

    @Test
    public void get_ReturnOptionalMagazine_WhenGivenMagazineIdExists() {
        Optional<Magazine> getMagazine = daoMagazineRepository.get(2);
        assertTrue(getMagazine.isPresent());
    }


    @Test
    public void get_ReturnsOptionalMagazine_AndCompareWithPreviousValue() {

        Optional<Magazine> nMagazine = daoMagazineRepository.get(4);
        assertTrue(nMagazine.isPresent());
        Magazine magazine1 = nMagazine.get();
        assertEquals(magazine1.getAuthor(), magazine.getAuthor());
    }

    @Test
    public void get_ReturnsOptionalEmpty_WhenGivenMagazineIdDoesNotExists() {
        Optional<Magazine> getMagazine = daoMagazineRepository.get(19);
        assertFalse(getMagazine.isPresent());
    }

    @Test
    public void save_ReturnInteger_WhenTheMagazineWasSaveCorrectly() {

        Magazine magazineTest = new Magazine("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "Maths", 1, "Princeton");
        magazineTest.setUserTaken(null);
        daoMagazineRepository.save(magazineTest);

        assertEquals(1, daoMagazineRepository.save(magazineTest));
    }

    @Test
    public void save() {

        Magazine magazineTest = new Magazine("Math Take 5 ", "Barton Frank", 987, LocalDate.now(), "Maths", 1, "Princeton");

        daoMagazineRepository.save(magazineTest);
        List<Magazine> magazineList = daoMagazineRepository.findAll();
        assertEquals(4, magazineList.size());

    }

    @Test
    public void update_ReturnsIntegerValue_WhenUpdateMagazineSuccess() {
        magazine.setTitle("Build F1 test");

        int checkUpdate = daoMagazineRepository.update(2, magazine);
        assertEquals(1, checkUpdate);
    }

    @Test
    public void update_ReturnIntegerValue_WhenDoesNotUpdateMagazineCorrectly() {

        magazine.setDateWrite(LocalDate.now().plusDays(2));

        int checkUpdate = daoMagazineRepository.update(10, magazine);
        assertEquals(0, checkUpdate);
    }



}
