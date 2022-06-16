package com.example.hamromistiri.Model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "mistiri_detail")
public class MistiriDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mistiri_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Valid
    @NotNull(message = "User information should be present")
    private User user;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mistiri_review_id", referencedColumnName = "mistiri_id")
    @Valid
    private List<Review> review;

    @Column(name ="pan_number")
    @NotEmpty(message = "Pan Number cannot be empty")
    @Length( min = 9,max = 9, message = "Pan Number should be of 9 digits")
    @Pattern(regexp = "^[1-9]+[0-9]*$" ,message = "Only number is allowed in pan number")
    private  String panNo;

    @Column(name ="rating")
    private int rating;

    @Column(name ="service")
    @NotEmpty(message = "Service field cannot be empty")
    private String service;

    @Column(name ="available_status")
    private Boolean availableStatus;

    @Column(name ="empolyee_status")
    @NotEmpty(message = "Employee Status cannot be empty")
    private String employeeStatus;

    @Column(name ="documents")
    @NotEmpty(message = "Document cannot be empty")
    private  String documents;

    @Column(name ="about_you")
    private String aboutYou;

    @Column(name="address")
    @NotEmpty(message = "address cannot be empty")
    private String address;

}
