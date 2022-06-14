package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> reviews(){
        return reviewRepository.findAll();
    }

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }


}
