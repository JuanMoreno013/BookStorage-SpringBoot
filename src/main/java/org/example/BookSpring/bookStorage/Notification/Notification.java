package org.example.BookSpring.bookStorage.Notification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Notification<T> implements Observer<T> {
    //Concrete subscriber

    private String messageNewItem;

    @Override
    public void update(String messageNewItem) {
        this.setMessageNewItem(messageNewItem);
        System.out.println(messageNewItem);
    }

}
