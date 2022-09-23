package org.example.BookSpring.bookStorage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TakenItemDto {

    private Integer user_taken;

    public TakenItemDto(Integer user_taken) {
        this.user_taken = user_taken;
    }
}
