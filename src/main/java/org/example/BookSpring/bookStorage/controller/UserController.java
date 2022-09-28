package org.example.BookSpring.bookStorage.controller;


import org.example.BookSpring.bookStorage.converter.UserConverter;
import org.example.BookSpring.bookStorage.dto.UserDto;
import org.example.BookSpring.bookStorage.models.User;
import org.example.BookSpring.bookStorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{userDtoId}")
    public UserDto getUser(@PathVariable Integer userDtoId) {
        return userService.get(userDtoId);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @DeleteMapping("/users/{userDtoId}")
    public Boolean deleteUser(@PathVariable Integer userDtoId) {
        return userService.delete(userDtoId);
    }

    @PutMapping("/users/{userDtoId}")
    public Boolean updateUser(@PathVariable Integer userDtoId, @RequestBody UserDto userDto) {
        return userService.update(userDtoId, userDto);
    }

}
