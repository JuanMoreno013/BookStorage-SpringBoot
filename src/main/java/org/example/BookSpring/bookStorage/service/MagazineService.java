package org.example.BookSpring.bookStorage.service;

import org.example.BookSpring.bookStorage.models.Magazine;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemBadRequestException;
import org.example.BookSpring.bookStorage.exceptionhandler.ItemNotFoundException;
import org.example.BookSpring.bookStorage.notification.NotificationCenter;
import org.example.BookSpring.bookStorage.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MagazineService {
    private final MagazineRepository repository;
    private final NotificationCenter notificationCenter;

    @Autowired
    public MagazineService(MagazineRepository repository, NotificationCenter notificationCenter) {
        this.repository = repository;
        this.notificationCenter = notificationCenter;
    }

    public List<Magazine> getAll() {
        return repository.findAll();
    }

    public Magazine get(int magazineId) {

        return repository.get(magazineId)
                .orElseThrow(() -> new ItemNotFoundException(" NOT found book with ID : " + magazineId));
    }

    public Boolean save(Magazine magazine) {
        Objects.requireNonNull(magazine);

        notificationCenter.sendNotification("magazine", "New Magazine Added !");
        return repository.save(magazine) == 1;

    }

    public Boolean delete(int magazineId) {
        if (magazineId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        notificationCenter.sendNotification("magazine", " Magazine Removed !");
        return repository.delete(magazineId) == 1;

    }

    public Boolean update(int magazineId, Magazine magazineItem) {

        return repository.update(magazineId, magazineItem) == 1;
    }

}
