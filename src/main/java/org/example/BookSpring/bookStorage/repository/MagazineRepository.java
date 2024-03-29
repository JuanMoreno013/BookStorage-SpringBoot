package org.example.BookSpring.bookStorage.repository;

import org.example.BookSpring.bookStorage.models.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MagazineRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Magazine> mapRow = (rs, rowNum) -> {
        Magazine magazine = new Magazine(
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("pages"),
                rs.getDate("dateMagazine").toLocalDate(),
                rs.getString("subject"),
                rs.getInt("volume"),
                rs.getString("editorial")
        );
        magazine.setId(rs.getInt("id_Magazine"));
        magazine.setUser_taken(rs.getObject("userTaken", Integer.class));

        return magazine;
    };


    public List<Magazine> findAll() {
        return jdbcTemplate.query("select * from Magazine", mapRow);
    }

    public void updateUserTaken(int id_Magazine, Magazine magazine) {
        String sqlUpdate = "update Magazine set userTaken = ? where id_Magazine = ? ";

        jdbcTemplate.update(sqlUpdate, magazine.getUser_taken(), id_Magazine);
    }

    public int update(int id_Magazine, Magazine magazine) {

        String sqlUpdateAll = "update Magazine set title = ?, author= ?, pages= ?, dateMagazine= ?, editorial= ?, volume =?, subject= ?,userTaken= ? " +
                " where id_Magazine = ?  ";

        return jdbcTemplate.update(
                sqlUpdateAll,
                magazine.getTitle(),
                magazine.getAuthor(),
                magazine.getPages(),
                Date.valueOf(magazine.getDate_write()),
                magazine.getEditorial(),
                magazine.getVolume(),
                magazine.getSubject(),
                magazine.getUser_taken(),
                id_Magazine);
    }


    public int save(Magazine magazine) {

        String sqlSave = "insert into Magazine " +
                "(title, author, pages, dateMagazine, editorial, volume, subject, userTaken) " +
                "values(?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sqlSave,
                magazine.getTitle(),
                magazine.getAuthor(),
                magazine.getPages(),
                Date.valueOf(magazine.getDate_write()),
                magazine.getEditorial(),
                magazine.getVolume(),
                magazine.getSubject(),
                magazine.getUser_taken());
    }

    public Optional<Magazine> get(int id_Magazine) {

        String sqlGetById = "select * from Magazine where id_Magazine = ? ";

        List<Magazine> magazines = jdbcTemplate.query(sqlGetById,
                mapRow, id_Magazine);

        return magazines.stream().findFirst();
    }

    public int delete(int id_Magazine) {
        String sqlDelete = "delete from Magazine where id_Magazine = ? ";

        return jdbcTemplate.update(sqlDelete, id_Magazine);
    }
}
