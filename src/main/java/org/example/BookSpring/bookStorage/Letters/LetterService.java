package org.example.BookSpring.bookStorage.Letters;


import org.example.BookSpring.bookStorage.Vexceptions.ItemBadRequestException;
import org.example.BookSpring.bookStorage.Vexceptions.ItemNotFoundException;
import org.example.BookSpring.bookStorage.Notification.NotificationCenter;
import org.example.BookSpring.repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;


@Service
public class LetterService {
    private final LetterRepository repository;
    private final NotificationCenter notificationCenter;

    @Autowired
    public LetterService(LetterRepository repository, NotificationCenter notificationCenter) {
        this.repository = repository;
        this.notificationCenter = notificationCenter;
    }

    public List<Letter> getAll() {
        return repository.findAll();
    }

    public Letter get(int letterId) {

        return repository.get(letterId)
                .orElseThrow(() -> new ItemNotFoundException(" NOT found letter with ID : " + letterId));
    }

    public Boolean save(Letter letter) {
        Objects.requireNonNull(letter);

        notificationCenter.sendNotification("letter", "New Letter Added !");
        return repository.save(letter) == 1;
    }

    public Boolean delete(int letterId) {
        if (letterId < 1)
            throw new ItemBadRequestException(" Wrong ID number! ");

        notificationCenter.sendNotification("letter", " Letter Removed !");
        return repository.delete(letterId) == 1;

    }

    public Boolean update(int letterId, Letter letterItem) {

        return repository.update(letterId, letterItem) == 1;
    }


}
