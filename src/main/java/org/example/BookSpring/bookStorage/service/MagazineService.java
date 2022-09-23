package org.example.BookSpring.bookStorage.service;

import org.example.BookSpring.bookStorage.converter.MagazineConverter;
import org.example.BookSpring.bookStorage.dto.MagazineDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.models.Magazine;
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

@Service
@Transactional
public class MagazineService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private NotificationCenter notificationCenter;
    @Autowired
    private MagazineConverter converter;


    public List<MagazineDto> getAll() {
        Query query = entityManager.createNamedQuery("query_find_all_magazines", Magazine.class);
        List daoMagazineList = query.getResultList();
        return converter.listEntityToDTO(daoMagazineList);

    }

    public MagazineDto get(int magazineDtoId) {
        return Optional.of(converter.entityToDTO(entityManager.find(Magazine.class, magazineDtoId)))
                .orElseThrow(() -> new ItemNotFoundException(" NOT found Magazine with ID : " + magazineDtoId));
    }

    public MagazineDto save(MagazineDto magazineDto) {
        Objects.requireNonNull(magazineDto);

        entityManager.persist(converter.dtoToEntity(magazineDto));
        notificationCenter.sendNotification("magazine", "New Magazine Added !");
        return magazineDto;
    }

    public Boolean delete(int magazineDtoId) {
        if (magazineDtoId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        Optional<Magazine> foundMagazine = Optional.of(entityManager.find(Magazine.class, magazineDtoId));
        foundMagazine.ifPresent((magazineId -> entityManager.remove(magazineId)));

        notificationCenter.sendNotification("magazine", " Magazine Removed !");
        return true;
    }

    public Boolean update(int magazineDtoId, MagazineDto magazineDto) {

        Optional<Magazine> foundBook = Optional.of(entityManager.find(Magazine.class, magazineDtoId));
        foundBook.ifPresent((magazine -> {

            Magazine magazineEntity = converter.dtoToEntity(magazineDto);
            magazineEntity.setId(magazineDtoId);
            entityManager.merge(magazineEntity);
        }));
        return true;
    }


    public Boolean updateMagazineTakenBy(int magazineDtoId, TakenItemDto takenItemDto) {

        Optional<Magazine> foundMagazine = Optional.of(entityManager.find(Magazine.class, magazineDtoId));
        foundMagazine.ifPresent((magazine -> {

            Magazine magazineEntity = foundMagazine.get();
            magazineEntity.setUser_taken(takenItemDto.getUser_taken());
            magazineEntity.setId(magazineDtoId);

            entityManager.merge(magazineEntity);
        }));
        return true;
    }


}
