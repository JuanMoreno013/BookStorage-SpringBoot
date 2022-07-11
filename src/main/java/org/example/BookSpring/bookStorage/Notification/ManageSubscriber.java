package org.example.BookSpring.bookStorage.Notification;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ManageSubscriber implements PublisherI {
    private final Map<String, List<Observer>> eventSub = new HashMap<>();

    public void subscribe(String eventType, Observer newSub) {

        Optional.ofNullable(eventSub.get(eventType))
                .ifPresentOrElse(subs -> subs.add(newSub), () -> {
                    List<Observer> listSubsForEvents = new ArrayList<>();
                    listSubsForEvents.add(newSub);
                    eventSub.put(eventType, listSubsForEvents);
                });
    }

    public void unsubscribe(String eventType, Observer oldSub) {
        Optional.ofNullable(eventSub.get(eventType))
                .ifPresent(subs -> subs.remove(oldSub));
    }

    public void notifySubscriber(String eventType, String message) {
        Optional.ofNullable(eventSub.get(eventType))
                .ifPresent(subs -> subs.forEach(sub -> sub.update(message)));
    }

}
