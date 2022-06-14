package com.example.hamromistiri.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
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
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mistiri_review_id", referencedColumnName = "mistiri_id")
    private List<Review> review;

    @Column(name ="pan_number")
    private  int panNo;

    @Column(name ="rating")
    private int rating;

    @Column(name ="service")
    private String service;

    @Column(name ="available_status")
    private Boolean availableStatus;

    @Column(name ="empolyee_status")
    private String employeeStatus;

    @Column(name ="documents")
    private  String documents;

    @Column(name ="about_you")
    private String aboutYou;

    @Column(name="address")
    private String address;

}
