package org.example.BookSpring.bookStorage.Notification;

public interface PublisherI<T> {
    void removeObserver(Observer<T> observer);
    void addObserver(Observer<T> observer);
    void notifyAllObservers();
}
