package org.example.BookSpring.bookStorage.services;

import org.example.BookSpring.bookStorage.Vexceptions.ItemBadRequestException;
import org.example.BookSpring.bookStorage.Vexceptions.ItemNotFoundException;
import org.example.BookSpring.bookStorage.model.User;
import org.example.BookSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User get(int userId) {

        return repository.get(userId)
                .orElseThrow(() -> new ItemNotFoundException(" NOT found user with ID : " + userId));
    }

    public Boolean save(User user) {
        Objects.requireNonNull(user);

        return repository.save(user) == 1;
    }

    public Boolean delete(int userId) {
        if (userId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

       return repository.delete(userId) ==1;
    }

    public Boolean update(int userId, User userItem) {
        return repository.update(userId, userItem) == 1;
    }

}
