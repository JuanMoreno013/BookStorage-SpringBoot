package org.example.BookSpring.bookStorage.service;

import org.example.BookSpring.bookStorage.converter.UserConverter;
import org.example.BookSpring.bookStorage.dto.UserDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserConverter converter;

    public List<UserDto> getAll() {
        Query query = entityManager.createNamedQuery("query_find_all_users", User.class);
        List daoUserList = query.getResultList();
        return converter.listEntityToDTO(daoUserList);
    }

    public UserDto save(UserDto userDto) {
        Objects.requireNonNull(userDto);

        User userI = converter.dtoToEntity(userDto);
        entityManager.persist(userI);
        return userDto;
    }

    public UserDto get(int userDtoId) {

        return Optional.of(converter.entityToDTO(entityManager.find(User.class, userDtoId)))
                .orElseThrow(() -> new ItemNotFoundException(" NOT found User with ID : " + userDtoId));

    }

    public Boolean delete(int userDtoId) {
        if (userDtoId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        Optional<User> foundUser = Optional.of(entityManager.find(User.class, userDtoId));
        foundUser.ifPresent((userId -> entityManager.remove(userId)));

        return true;
    }

    public Boolean update(int userDtoId, UserDto userDto) {

        Optional<User> foundUser = Optional.of(entityManager.find(User.class, userDtoId));

        foundUser.ifPresent((book -> {

            User userEntity = converter.dtoToEntity(userDto);
            userEntity.setId(userDtoId);
            entityManager.merge(userEntity);
        }));
        return true;

    }

}
