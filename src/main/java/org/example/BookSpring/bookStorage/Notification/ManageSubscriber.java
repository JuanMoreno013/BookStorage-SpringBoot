package org.example.BookSpring.bookStorage.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

//EventManager
@Component
public class ManageSubscriber implements PublisherI{

   private final Map<String, List<Observer>> eventSub;
    private final SubscriberConfig subscriberConfig;

    @Autowired
    public ManageSubscriber(SubscriberConfig subscriberConfig) {

        this.subscriberConfig = subscriberConfig;
        this.eventSub = subscriberConfig.allEvents();
    }

    public void subscribe(String eventType, Observer newSub){

        Optional.ofNullable(eventSub.get(eventType))
                .ifPresent( subs -> subs.add(newSub));

//        List<Observer> user = eventSub.get(eventType);
//        user.add(newSub);
    }

    public void unsubscribe (String eventType, Observer oldSub){


        Optional.ofNullable(eventSub.get(eventType))
                .ifPresent( subs -> subs.remove(oldSub));

//        List<Observer> user = eventSub.get(eventType);
//        user.remove(oldSub);
    }

    public void notifySubscriber(String eventType, String message){

        Optional.ofNullable(eventSub.get(eventType))
                .ifPresent(subs -> subs. forEach(sub-> sub.update(message)));
    }

}
