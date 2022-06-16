package com.example.hamromistiri.Model;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    @NotEmpty(message = "FirstName cannot be null")
    private String firstName;

    @Column(name ="last_name")
    @NotEmpty(message = "LastName cannot be null")
    private String lastName;

    @Column(name ="email")
    @NotEmpty(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "pattern didn't matched for email")
    private String email;

    @Column(name ="password")
    @NotEmpty(message = "Password cannot be null")
    private String password;

    @Column(name ="role")
    @NotEmpty(message = "Role cannot be null")
    private String role;

    @Column(name ="phone_number")
    @NotEmpty(message = "Email cannot be null")
    @Length(min = 10, max = 10, message = "Phone number needs to be of 10 digits")
    @Pattern(regexp = "^[1-9]+[0-9]*$" ,message = "Only number is allowed in phoneNo")

    private String phoneNo;

}
