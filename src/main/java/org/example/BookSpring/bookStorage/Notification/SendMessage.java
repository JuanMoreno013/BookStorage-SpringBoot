package org.example.BookSpring.bookStorage.Notification;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//Publisher
@Getter
@Setter
public abstract class SendMessage<T> implements PublisherI<T>{
    private String message;

    private final List<Observer<T>> observers = new ArrayList<>();

    public void removeObserver(Observer<T> observer){
        observers.remove(observer);
    }

    public void addObserver(Observer<T> observer){
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer<T> observer : observers) {
            observer.update(message);
        }
    }

}
