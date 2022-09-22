package org.example.BookSpring.bookStorage.converter;

import org.example.BookSpring.bookStorage.dto.UserDto;
import org.example.BookSpring.bookStorage.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDto entityToDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public List<UserDto> listEntityToDTO(List<User> listUser) {
        return listUser
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<User> listDtoToEntity(List<UserDto> listUserDto) {
        return listUserDto
                .stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
