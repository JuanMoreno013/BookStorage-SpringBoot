package org.example.BookSpring.bookStorage.Notification;


public interface Observer<T> { //subscriber
    void update(String message);

//    void update(T message)
}
