package org.example.BookSpring.bookStorage.notification.subscribers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.notification.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@AllArgsConstructor
@Getter
@Setter
@Component
public class Subscriber implements Observer {
    private String message;

    Logger logger = Logger.getLogger("Alert Service!");

    @Autowired
    public Subscriber() {
    }

    @Override
    public void update(String message) {
        this.message = message;
        logger.info(message);
    }
}
