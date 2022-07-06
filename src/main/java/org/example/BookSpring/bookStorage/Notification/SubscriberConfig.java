package org.example.BookSpring.bookStorage.Notification;

import org.example.BookSpring.bookStorage.Notification.subscribers.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubscriberConfig {

    private final Subscriber subscriber;

    @Autowired
    public SubscriberConfig(Subscriber subscriber){
            this.subscriber = subscriber;
    }

    public Map<String, List<Observer>> allEvents(){

        Map<String, List<Observer>> eventSub = new HashMap<>();

        List<Observer> observers = new ArrayList<>();
        observers.add(subscriber);

        eventSub.put("book", observers);
        eventSub.put("magazine", observers);
        eventSub.put("letter", observers);


        return eventSub;
    }

}
