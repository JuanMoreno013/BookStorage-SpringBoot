package org.example.BookSpring.bookStorage.controller;

import org.example.BookSpring.bookStorage.service.BookService;
import org.example.BookSpring.bookStorage.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable Integer bookId) {
        return bookService.get(bookId);
    }

    @PostMapping("/books")
    public Boolean addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/books/{bookId}")
    public Boolean deleteBook(@PathVariable Integer bookId) {
        return bookService.delete(bookId);
    }

    @PutMapping("/books/{bookId}")
    public Boolean updateBook(@PathVariable Integer bookId, @RequestBody Book book) {
        return bookService.update(bookId, book);
    }

}
