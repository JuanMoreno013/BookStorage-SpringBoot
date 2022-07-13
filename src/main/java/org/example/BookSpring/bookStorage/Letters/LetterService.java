package org.example.BookSpring.bookStorage.Letters;


import org.example.BookSpring.bookStorage.Vexceptions.ItemBadRequestException;
import org.example.BookSpring.bookStorage.Vexceptions.ItemNotFoundException;
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
        return Optional.ofNullable(repository.get(letterId)
                .map(item -> (Letter) item)
                .orElseThrow(() -> new ItemNotFoundException("NOT found letter with ID : " + letterId)));
    }

    public Boolean add(ItemOp objItem) {
        Objects.requireNonNull(objItem);

        notificationCenter.sendNotification("letter", "New Letter Added !");

        repository.add(objItem.getId(), objItem);
        return true;
    }

    public Boolean delete(int letterId) {
        if (letterId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        boolean CheckLetter = repository.get(letterId)
                .filter(item -> item instanceof Letter)
                .isPresent();

        if (CheckLetter) {
            repository.remove(letterId);
            notificationCenter.sendNotification("letter", "Letter Removed !");
            return true;
        }

        throw new ItemNotFoundException(" NOT found letter with ID " + letterId + " to be removed !");

    }

    public Optional<ItemOp> update(int letterId, ItemOp letterItem) {

        boolean CheckLetter = repository.get(letterId)
                .filter(item -> item instanceof Letter)
                .isPresent();

        if (!CheckLetter) {
            throw new ItemNotFoundException(" NOT found book with ID " + letterId + " to be updated !");
        }

        letterItem.setId(letterId);
        return repository.update(letterId, letterItem);
    }

}
