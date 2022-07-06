package org.example.BookSpring.bookStorage.Notification.subscribers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.BookSpring.bookStorage.Notification.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Component
public class Subscriber implements Observer {
private String message;


@Autowired
    public Subscriber() {
    }

    @Override
    public void update(String message) {
        this.message = message;
        System.out.println(" \t" + message);
//        System.out.println(" \t New Book Added");
    }
}
