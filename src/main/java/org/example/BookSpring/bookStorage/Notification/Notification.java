package org.example.BookSpring.bookStorage.Notification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Notification implements Observer {
    //Concrete subscriber

    private String messageNewItem;

    @Override
    public void update(String messageNewItem) {
        this.setMessageNewItem(messageNewItem);
        System.out.println(messageNewItem);
    }

//    public Notification(String message){
//        this.messageNewItem= message;
//    }

}
