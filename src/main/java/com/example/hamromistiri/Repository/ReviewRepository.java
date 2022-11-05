package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {


    @Query(nativeQuery = true, value = "select * from review u where  u.mistiri_review_id = ?1 and u.user_review_id = ?2")
    Review findReviewByMistriAndCustomerId(Integer mistiriId,Integer customerId);

    @Query("select u from Review u where u.mistiriDetail.id = :num")
    List<Review> findReviewFromMistiriId(int num);

    @Modifying
    @Query(nativeQuery = true, value = "delete \n" +
            "from review u\n" +
            "where u.user_review_id = ?1\n")
    void deleteReviewByCustomerId(int uid);

    @Modifying
    @Query(nativeQuery = true, value = "delete \n" +
            "from review u\n" +
            "where u.mistiri_review_id = ?1\n")
    void deleteReviewByMistiriId(int mid);


}
