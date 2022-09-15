package org.example.BookSpring.bookStorage.notification;

import org.example.BookSpring.bookStorage.notification.subscribers.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfig {

    @Autowired
    public SubscriberConfig(Subscriber subscriber, ManageSubscriber manageSubscriber) {

        manageSubscriber.subscribe("book", subscriber);
        manageSubscriber.subscribe("magazine", subscriber);
        manageSubscriber.subscribe("letter", subscriber);
    }
}
