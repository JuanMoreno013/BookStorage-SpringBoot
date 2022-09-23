package org.example.BookSpring.bookStorage.controller;

import org.example.BookSpring.bookStorage.dto.BookDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/books/{bookDtoId}")
    public BookDto getBook(@PathVariable Integer bookDtoId) {
        return bookService.get(bookDtoId);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @DeleteMapping("/books/{bookDtoId}")
    public Boolean deleteBook(@PathVariable Integer bookDtoId) {
        return bookService.delete(bookDtoId);
    }

    @PutMapping("/books/{bookDtoId}")
    public Boolean updateBook(@PathVariable Integer bookDtoId, @RequestBody BookDto bookDto) {
        return bookService.update(bookDtoId, bookDto);
    }

    @PutMapping("/books/{bookDtoId}/takenBy")
    public Boolean updateBookTakenByUser(@PathVariable Integer bookDtoId, @RequestBody TakenItemDto takenItemDto) {
        return bookService.updateBookTakenBy(bookDtoId, takenItemDto);
    }

}
