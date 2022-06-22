package com.example.hamromistiri.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "Comment is required")
    private String comment;

    @Column(name = "indivusal_rating")
    private int indivisualRating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_review_id", referencedColumnName = "id")
    @NotNull(message = "User info is must for giving an review")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mistiriReview_id", referencedColumnName = "mistiri_id")
    private MistiriDetail mistiriDetail;

}
