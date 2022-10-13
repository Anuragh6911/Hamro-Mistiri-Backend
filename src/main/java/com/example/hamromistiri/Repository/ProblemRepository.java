package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Integer> {
}