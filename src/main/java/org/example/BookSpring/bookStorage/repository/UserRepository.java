package org.example.BookSpring.bookStorage.repository;

import org.example.BookSpring.bookStorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<User> mapRow = (rs, rowNum) -> {
        User user = new User(
                rs.getString("userName"),
                rs.getDate("dateBirth").toLocalDate(),
                rs.getDate("dateTakeItem").toLocalDate()

        );
        user.setId(rs.getInt("id_User"));

        return user;
    };

    public List<User> findAll() {
        return jdbcTemplate.query("select * from \"User\"", mapRow);
    }

    public int update(int id_User, User user) {

        String sqlUpdateAll = "update \"User\" set userName = ?, dateBirth= ?, dateTakeItem= ?" +
                " where id_User = ?  ";

       return jdbcTemplate.update(
                sqlUpdateAll,
                user.getName(),
                user.getDateOfBirth(),
                user.getDateItemTaken(),
                id_User);
    }


    public int save(User user) {

        String sqlSave = "insert into \"User\" " +
                "(userName, dateBirth, dateTakeItem) " +
                "values(?,?,?)";

        return jdbcTemplate.update(sqlSave,
                user.getName(),
                user.getDateOfBirth(),
                user.getDateItemTaken());
    }

    public Optional<User> get(int id_User) {

        String sqlGetById = "select * from \"User\" where id_User = ? ";

        List<User> users = jdbcTemplate.query(sqlGetById,
                mapRow, id_User);

        if (!users.isEmpty())
            return users.stream().findFirst();

        return Optional.empty();
    }

    public int delete(int id_User) {
        String sqlDelete = "delete from \"User\" where id_User = ? ";

        return jdbcTemplate.update(sqlDelete, id_User);
    }

}
