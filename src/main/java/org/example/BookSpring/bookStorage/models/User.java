package org.example.BookSpring.bookStorage.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@NamedQuery(query = "select u from User u", name = "query_find_all_users")
public final class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "date_birth")
    private LocalDate dateBirth;
    @Column(name = "date_take_item")
    private LocalDate dateTakeItem;

    private static ChainValidator validateChain = new ChainValidator();

    public User(String userName, LocalDate dateBirth, LocalDate dateTakeItem) {
        this.userName = userName;
        this.dateBirth = validateChain.processValidator(dateBirth);
        this.dateTakeItem = validateChain.processValidator(dateTakeItem);
    }


}
