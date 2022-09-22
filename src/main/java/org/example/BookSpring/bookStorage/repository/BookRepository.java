package org.example.BookSpring.bookStorage.repository;

import org.example.BookSpring.bookStorage.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final RowMapper<Book> mapRow = (rs, rowNum) -> {
        Book book = new Book(
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("pages"),
                rs.getDate("dateBook").toLocalDate(),
                rs.getString("subject"),
                rs.getString("nsbn"),
                rs.getString("editorial"),
                rs.getString("status")
        );
        book.setId(rs.getInt("id_Book"));
        book.setUser_taken(rs.getObject("userTaken", Integer.class));

        return book;
    };


    public List<Book> findAll() {
        return jdbcTemplate.query("select * from Book ", mapRow);
    }


    public int update(int id_Book, Book book) {

        String sqlUpdateAll = "update Book set title = ?, author= ?, pages= ?, dateBook= ?, nsbn= ?, subject= ?, status= ?, editorial= ?,userTaken= ? " +
                " where id_Book = ?  ";

        return jdbcTemplate.update(
                sqlUpdateAll,
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                Date.valueOf(book.getDate_write()),
                book.getNsbn(),
                book.getSubject(),
                book.getStatus(),
                book.getEditorial(),
                book.getUser_taken(),
                id_Book);

    }


    public int save(Book book) {

        String sqlSave = "insert into Book " +
                "(title, author, pages, dateBook, nsbn, subject, status, editorial,userTaken) " +
                "values(?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sqlSave,
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                Date.valueOf(book.getDate_write()),
                book.getNsbn(),
                book.getSubject(),
                book.getStatus(),
                book.getEditorial(),
                book.getUser_taken());
    }

    public Optional<Book> get(int id_Book) {

        String sqlGetById = "select * from Book where id_Book = ? ";

        List<Book> books = jdbcTemplate.query(sqlGetById,
                mapRow, id_Book);

        return books.stream().findFirst();
    }

    public int delete(int id_Book) {
        String sqlDelete = "delete from Book where id_Book = ? ";

        return jdbcTemplate.update(sqlDelete, id_Book);
    }


}
