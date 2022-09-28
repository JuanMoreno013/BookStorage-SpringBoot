package org.example.BookSpring.bookStorage.converter;

import org.example.BookSpring.bookStorage.dto.BookDto;
import org.example.BookSpring.bookStorage.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BookDto entityToDTO(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book dtoToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public List<BookDto> listEntityToDTO(List<Book> listBook) {
        return listBook
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<Book> listDtoToEntity(List<BookDto> listBookDto) {
        return listBookDto
                .stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
