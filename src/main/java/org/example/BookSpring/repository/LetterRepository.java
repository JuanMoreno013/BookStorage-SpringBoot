package org.example.BookSpring.repository;

import org.example.BookSpring.bookStorage.Letters.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LetterRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LetterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Letter> mapRow = (rs, rowNum) -> {
        Letter magazine = new Letter(
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("pages"),
                rs.getDate("dateLetter").toLocalDate(),
                rs.getString("subject"),
                rs.getString("place")
        );
        magazine.setId(rs.getInt("id_Letter"));
        magazine.setUserTaken(rs.getObject("userTaken", Integer.class));

        return magazine;
    };


    public List<Letter> findAll() {
        return jdbcTemplate.query("select * from \"Letter\"", mapRow);
    }

    public void updateUserTaken(int id_Letter, Letter magazine) {
        String sqlUpdate = "update \"Letter\" set userTaken = ? where id_Letter = ? ";

        jdbcTemplate.update(sqlUpdate, magazine.getUserTaken(), id_Letter);
    }

    public int update(int id_Letter, Letter magazine) {

        String sqlUpdateAll = "update \"Letter\" set title = ?, author= ?, pages= ?, dateLetter= ?, subject= ?, place= ?, userTaken= ? " +
                " where id_Letter = ?  ";

       return jdbcTemplate.update(
                sqlUpdateAll,
                magazine.getTitle(),
                magazine.getAuthor(),
                magazine.getPages(),
                magazine.getDateWrite(),
                magazine.getSubject(),
                magazine.getPlace(),
                magazine.getUserTaken(),
                id_Letter);
    }


    public int save(Letter magazine) {

        String sqlSave = "insert into \"Letter\" " +
                "(title, author, pages, dateLetter, subject, place, userTaken) " +
                "values(?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sqlSave,
                magazine.getTitle(),
                magazine.getAuthor(),
                magazine.getPages(),
                magazine.getDateWrite(),
                magazine.getSubject(),
                magazine.getPlace(),
                magazine.getUserTaken());
    }

    public Optional<Letter> get(int id_Letter) {

        String sqlGetById = "select * from \"Letter\" where id_Letter = ? ";

        List<Letter> magazines = jdbcTemplate.query(sqlGetById,
                mapRow, id_Letter);

        if (!magazines.isEmpty())
            return magazines.stream().findFirst();

        return Optional.empty();
    }

    public int delete(int id_Letter) {
        String sqlDelete = "delete from \"Letter\" where id_Letter = ? ";

        return jdbcTemplate.update(sqlDelete, id_Letter);
    }
}
