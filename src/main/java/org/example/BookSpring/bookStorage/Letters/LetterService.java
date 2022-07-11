package org.example.BookSpring.bookStorage.Letters;


import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Notification.NotificationCenter;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LetterService {
    private final Repository<ItemOp> repository;
    private final NotificationCenter notificationCenter;

    @Autowired
    public LetterService(@Qualifier("hash") Repository<ItemOp> repository, NotificationCenter notificationCenter) {
        this.repository = repository;
        this.notificationCenter = notificationCenter;
    }

    public List<ItemOp> getAll() {
        return repository.getAll()
                .stream()
                .filter(e -> e instanceof Letter)
                .collect(Collectors.toList());
    }

    public Optional<ItemOp> get(int letterId) {
        return repository.get(letterId);
    }

    public Boolean add(ItemOp objItem) {
        Objects.requireNonNull(objItem);

        notificationCenter.sendNotification("letter", "New Letter Added !");

        repository.add(objItem.getId(), objItem);
        return true;
    }

    public Boolean delete(int letterId) {
        if (letterId < 0)
            throw new IllegalArgumentException();

        boolean CheckLetter = repository.get(letterId)
                .filter(item -> item instanceof Letter)
                .isPresent();

        if (CheckLetter) {
            repository.remove(letterId);
            notificationCenter.sendNotification("letter", "Letter Removed !");
            return true;
        }
        return false;
    }

    public Optional<ItemOp> update(int letterId, ItemOp letterItem) {

        boolean CheckLetter = repository.get(letterId)
                .filter(item -> item instanceof Letter)
                .isPresent();

        if (!CheckLetter) {
            return Optional.empty();
        }

        letterItem.setId(letterId);
        return repository.update(letterId, letterItem);
    }

}
