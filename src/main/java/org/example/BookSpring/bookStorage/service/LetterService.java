package org.example.BookSpring.bookStorage.service;


import org.example.BookSpring.bookStorage.converter.LetterConverter;
import org.example.BookSpring.bookStorage.dto.LetterDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.Letter;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@Service
@Transactional
public class LetterService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private NotificationCenter notificationCenter;
    @Autowired
    private LetterConverter converter;


    public List<LetterDto> getAll() {
        Query query = entityManager.createNamedQuery("query_find_all_letters", Letter.class);
        List daoLetterList = query.getResultList();
        return converter.listEntityToDTO(daoLetterList);

    }

    public LetterDto get(int letterDtoId) {
        return Optional.ofNullable(converter.entityToDTO(entityManager.find(Letter.class, letterDtoId)))
                .orElseThrow(() -> new ItemNotFoundException(" NOT found Book with ID : " + letterDtoId));
    }

    public LetterDto save(LetterDto letterDto) {
        Objects.requireNonNull(letterDto);

        entityManager.persist(converter.dtoToEntity(letterDto));
        notificationCenter.sendNotification("letter", "New Letter Added !");
        return letterDto;

    }

    public Boolean delete(int letterDtoId) {
        AtomicBoolean checkDelete = new AtomicBoolean(true);

        if (letterDtoId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        Optional<Letter> foundLetter = Optional.ofNullable(entityManager.find(Letter.class, letterDtoId));
        foundLetter.ifPresentOrElse((letterId -> entityManager.remove(letterId)), () -> checkDelete.getAndSet(false));

        notificationCenter.sendNotification("letter", " Letter Removed !");
        return checkDelete.get();
    }

    public Boolean update(int letterDtoId, LetterDto letterDto) {

        if (entityManager.find(Letter.class, letterDtoId) != null) {

            Letter letterEntity = converter.dtoToEntity(letterDto);
            letterEntity.setId(letterDtoId);
            entityManager.merge(letterEntity);
            return true;
        }
        return false;
    }

    public Boolean updateLetterTakenBy(int letterDtoId, TakenItemDto takenItemDto) {

        Letter letter = entityManager.find(Letter.class, letterDtoId);

        if (letter != null) {
            letter.setUser_taken(takenItemDto.getUser_taken());
            letter.setId(letterDtoId);
            entityManager.merge(letter);

            return true;
        }
        return false;
    }


}
