package org.example.BookSpring.bookStorage.converter;

import org.example.BookSpring.bookStorage.dto.LetterDto;
import org.example.BookSpring.bookStorage.models.Letter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LetterConverter {
    @Autowired
    private ModelMapper modelMapper;

    public LetterDto entityToDTO(Letter letter) {
        return modelMapper.map(letter, LetterDto.class);
    }

    public Letter dtoToEntity(LetterDto letterDto) {
        return modelMapper.map(letterDto, Letter.class);
    }

    public List<LetterDto> listEntityToDTO(List<Letter> listLetter) {
        return listLetter
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<Letter> listDtoToEntity(List<LetterDto> listLetterDto) {
        return listLetterDto
                .stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
