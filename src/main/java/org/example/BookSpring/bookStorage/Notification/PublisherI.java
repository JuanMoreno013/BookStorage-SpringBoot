package org.example.BookSpring.bookStorage.Notification;

public interface PublisherI {
    void removeObserver(Observer observer);
    void addObserver(Observer observer);
    void notifyAllObservers();
}
