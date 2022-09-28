package org.example.BookSpring.bookStorage.converter;

import org.example.BookSpring.bookStorage.dto.BookDto;
import org.example.BookSpring.bookStorage.dto.MagazineDto;
import org.example.BookSpring.bookStorage.models.Book;
import org.example.BookSpring.bookStorage.models.Magazine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MagazineConverter {
    @Autowired
    private ModelMapper modelMapper;

    public MagazineDto entityToDTO(Magazine magazine) {
        return modelMapper.map(magazine, MagazineDto.class);
    }

    public Magazine dtoToEntity(MagazineDto magazineDto) {
        return modelMapper.map(magazineDto, Magazine.class);
    }

    public List<MagazineDto> listEntityToDTO(List<Magazine> listMagazine) {
        return listMagazine
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<Magazine> listDtoToEntity(List<MagazineDto> listMagazineDto) {
        return listMagazineDto
                .stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
