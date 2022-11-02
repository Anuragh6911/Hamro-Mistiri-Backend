package com.example.hamromistiri.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="user_problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="problem_id")
    private int id;

    private String category;

    private String urgency;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mistiri_id",referencedColumnName = "mistiri_id")
    private MistiriDetail mistiriDetail;

}
