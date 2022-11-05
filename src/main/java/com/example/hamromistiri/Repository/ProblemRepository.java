package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Problem;
import com.example.hamromistiri.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Integer> {

    @Query(nativeQuery = true, value = "select *\n" +
            "from user_problem u\n" +
            "where u.mistiri_id = ?1\n")
    List<Problem> findProblemByMistiriId(int mid);

    @Query(nativeQuery = true, value = "select *\n" +
            "from user_problem u\n" +
            "where u.customer_id = ?1\n")
    List<Problem> findProblemByCustomerId(int mid);

    @Modifying
    @Query(nativeQuery = true, value = "delete \n" +
            "from user_problem u\n" +
            "where u.customer_id = ?1\n")
    void deleteProblemByCustomerId(int uid);

    // delete problems when mistiri is deleted.
    @Modifying
    @Query(nativeQuery = true, value = "delete \n" +
            "from user_problem u\n" +
            "where u.mistiri_id = ?1\n")
    void deleteProblemByMistiriId(int mid);
}