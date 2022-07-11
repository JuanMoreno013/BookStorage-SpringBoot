package org.example.BookSpring.bookStorage.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationCenter {
    public ManageSubscriber event;

    @Autowired
    public NotificationCenter(ManageSubscriber manageSubscriber) {
        this.event = manageSubscriber;
    }

    public void sendNotification(String eventType, String message) {
        event.notifySubscriber(eventType, message);
    }
}
