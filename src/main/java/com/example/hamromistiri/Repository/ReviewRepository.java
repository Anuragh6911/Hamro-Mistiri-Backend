package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("select u from Review u where u.mistiriDetail.id = :num")
    List<Review> findReviewFromMistiriId(int num);

}
