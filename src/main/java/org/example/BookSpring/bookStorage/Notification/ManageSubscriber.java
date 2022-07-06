package org.example.BookSpring.bookStorage.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Observer> user = eventSub.get(eventType);
        user.add(newSub);
    }

    public void unsubscribe (String eventType, Observer oldSub){
        List<Observer> user = eventSub.get(eventType);
        user.add(oldSub);
    }

    public void notifySubscriber(String eventType, String message){
        List<Observer> user = eventSub.get(eventType);

        for (Observer observers : user){
            observers.update(message);
        }

    }

}
