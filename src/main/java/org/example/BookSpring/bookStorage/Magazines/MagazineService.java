package org.example.BookSpring.bookStorage.Magazines;


import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Notification.Observer;
import org.example.BookSpring.bookStorage.Notification.SendMessage;
import org.example.BookSpring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MagazineService<T> extends SendMessage<T> {
    private final Repository<ItemOp> repository;

    @Autowired
    public MagazineService(Repository<ItemOp> repository, Observer<T> observer) {
        addObserver(observer);
        this.repository = repository;
    }

    public List<Magazine> getAll() {

        return repository.getAll().
                stream()
                .filter(e-> e instanceof Magazine)
                .map(item -> (Magazine) item)
                .collect(Collectors.toList());
    }

    public Optional<Magazine> get(int magazineId) {
        return repository.get(magazineId)
                .map(item -> (Magazine) item);
    }

    public Boolean add(ItemOp objItem){
        Objects.requireNonNull(objItem);

        setMessage("New Magazine Added");
        notifyAllObservers();

        repository.add(objItem.getId(),objItem);
        return true;
    }

    public Boolean delete(int magazineId){
        if(magazineId<0)
            throw new IllegalArgumentException();

        boolean CheckMagazine = repository.get(magazineId)
                .filter(item -> item instanceof Magazine)
                .isPresent();

        if (CheckMagazine) {
            repository.remove(magazineId);
            setMessage("Magazine Removed! ");
            notifyAllObservers();
            return true;
        }

        return false;
    }

    public Optional<Magazine> update(int magazineId, ItemOp magazineItem){

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
