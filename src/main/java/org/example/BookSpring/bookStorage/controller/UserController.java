package org.example.BookSpring.bookStorage.controller;


import org.example.BookSpring.bookStorage.models.User;
import org.example.BookSpring.bookStorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return userService.get(userId);
    }

    @PostMapping("/users")
    public Boolean addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public Boolean deleteUser(@PathVariable Integer userId) {
        return userService.delete(userId);
    }

    @PutMapping("/users/{userId}")
    public Boolean updateUser(@PathVariable Integer userId, @RequestBody User user) {
        return userService.update(userId, user);
    }

}
