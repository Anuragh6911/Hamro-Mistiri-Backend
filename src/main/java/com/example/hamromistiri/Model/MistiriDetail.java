package com.example.hamromistiri.Model;

import lombok.*;
import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "mistiri_detail")
public class MistiriDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mistiri_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name ="pan_number")
    private  String panNo;

    @Column(name ="rating")
    private Double rating;

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

    private Integer count;

}
