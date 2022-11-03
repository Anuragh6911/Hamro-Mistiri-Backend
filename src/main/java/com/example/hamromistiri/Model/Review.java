package com.example.hamromistiri.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="comment")
    private String comment;

    @Column(name = "indivusal_rating")
    private Double indivisualRating;

    @OneToOne(cascade = CascadeType.MERGE)//this merges with the user id so new user wont be created!! You need to pass id here !
    @JoinColumn(name = "user_review_id", referencedColumnName = "id")
    @Valid
    private Customer customer;

     //many to one maping left to see
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mistiriReview_id", referencedColumnName = "mistiri_id")
    private MistiriDetail mistiriDetail;

}
