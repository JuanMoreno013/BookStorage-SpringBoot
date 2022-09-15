package org.example.BookSpring.bookStorage.models;


import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

//@Entity
//@Table(name = "User")
@Getter
@Setter
public final class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private final String name;
    private final LocalDate dateOfBirth;
    private final LocalDate dateItemTaken;

    private static ChainValidator validateChain = new ChainValidator();

    public User(String name, LocalDate dateOfBirth, LocalDate dateItemTaken) {
        this.name = name;
        this.dateOfBirth = validateChain.processValidator(dateOfBirth);
        this.dateItemTaken = validateChain.processValidator(dateItemTaken);
    }




}
