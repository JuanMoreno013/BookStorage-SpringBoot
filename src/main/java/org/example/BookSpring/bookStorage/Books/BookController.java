package org.example.BookSpring.bookStorage.Books;

import org.example.BookSpring.bookStorage.ItemNotFoundException;
import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.StorageNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController  {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<ItemOp> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/books/{bookId}")
    public ItemOp getBook(@PathVariable Integer bookId){
        return bookService.get(bookId)
                .map(item -> (Book) item)
                .orElseThrow(() -> new ItemNotFoundException(bookId));
    }

    @PostMapping("/books")
    public Boolean addBook(@RequestBody Book book){
        return bookService.add(book);
    }

    @DeleteMapping("/books/{bookId}")
    public Boolean deleteBook(@PathVariable Integer bookId){
        return bookService.delete(bookId);
    }

    @PutMapping("/books/{bookId}")
    public ItemOp updateBook(@PathVariable Integer bookId, @RequestBody Book nbook)
    {
        return bookService.update(bookId, nbook)
                .map(book -> {
                    book.setId(nbook.getId());
                    book.setTitle(nbook.getTitle());
                    book.setAuthor(nbook.getAuthor());
                    book.setDateWrite(nbook.getDateWrite());
                    book.setPages(nbook.getPages());

                    return (Book) book;
                })
                .orElseThrow(() ->new IllegalArgumentException(""));
    }

}
