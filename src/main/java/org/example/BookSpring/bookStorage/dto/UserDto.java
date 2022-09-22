package org.example.BookSpring.bookStorage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;
    private String userName;
    private LocalDate dateBirth;
    private LocalDate dateTakeItem;

    private static ChainValidator validateChain = new ChainValidator();

    public UserDto(String userName, LocalDate dateBirth, LocalDate dateTakeItem) {
        this.userName = userName;
        this.dateBirth = validateChain.processValidator(dateBirth);
        this.dateTakeItem = validateChain.processValidator(dateTakeItem);
    }
}
