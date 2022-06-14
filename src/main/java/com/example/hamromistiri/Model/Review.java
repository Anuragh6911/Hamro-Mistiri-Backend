package com.example.hamromistiri.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reviewId")
    private int id;

    @Column(name="comment")
    private String comment;

    @Column(name = "indivusal_rating")
    private int indivisualRating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_review_id", referencedColumnName = "id")
    private User user;

}
