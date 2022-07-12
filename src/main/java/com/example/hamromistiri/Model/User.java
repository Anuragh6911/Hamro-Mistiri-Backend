package com.example.hamromistiri.Model;
import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="email")
    private String email;

    @Column(name ="password")
    private String password;

    @Column(name ="role")
    private String role;

    @Column(name ="phone_number")
    private String phoneNo;

}
