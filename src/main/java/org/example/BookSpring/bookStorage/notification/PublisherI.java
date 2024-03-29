package org.example.BookSpring.bookStorage.notification;

public interface PublisherI {

    void subscribe(String eventType, Observer newSub);

    void unsubscribe(String eventType, Observer oldSub);

    void notifySubscriber(String eventType, String message);

}
