package org.example.BookSpring.bookStorage.Notification;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//Publisher
@Getter
@Setter
public abstract class SendMessage implements PublisherI{
    private String message;

    private final List<Observer> observers = new ArrayList<>();

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
//        System.out.println(this.message);
    }

}
