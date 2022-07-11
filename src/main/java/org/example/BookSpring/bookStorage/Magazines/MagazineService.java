package org.example.BookSpring.bookStorage.Magazines;

import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Notification.NotificationCenter;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MagazineService<T> {
    private final Repository<ItemOp> repository;
    private final NotificationCenter notificationCenter;

    @Autowired
    public MagazineService(Repository<ItemOp> repository, NotificationCenter notificationCenter) {
        this.repository = repository;
        this.notificationCenter = notificationCenter;
    }

    public List<Magazine> getAll() {

        return repository.getAll().
                stream()
                .filter(e -> e instanceof Magazine)
                .map(item -> (Magazine) item)
                .collect(Collectors.toList());
    }

    public Optional<Magazine> get(int magazineId) {
        return repository.get(magazineId)
                .map(item -> (Magazine) item);
    }

    public Boolean add(ItemOp objItem) {
        Objects.requireNonNull(objItem);

        notificationCenter.sendNotification("magazine", "New Magazine Added !");
        repository.add(objItem.getId(), objItem);
        return true;
    }

    public Boolean delete(int magazineId) {
        if (magazineId < 0)
            throw new IllegalArgumentException();

        boolean CheckMagazine = repository.get(magazineId)
                .filter(item -> item instanceof Magazine)
                .isPresent();

        if (CheckMagazine) {
            repository.remove(magazineId);
            notificationCenter.sendNotification("magazine", " Magazine Removed !");
            return true;
        }
        return false;
    }

    public Optional<Magazine> update(int magazineId, ItemOp magazineItem) {

        boolean CheckMagazine = repository.get(magazineId)
                .filter(item -> item instanceof Magazine)
                .isPresent();

        if (!CheckMagazine) {
            return Optional.empty();
        }
        magazineItem.setId(magazineId);

        return repository.update(magazineId, magazineItem)
                .map(item -> (Magazine) item);
    }
}
