package com.example.hamromistiri.Controller;

import com.example.hamromistiri.HamroMistiriApplication;
import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/findAllReviews")
    public List<Review> findByID(){
        return reviewService.reviews();
    }

    @PostMapping("/addReview")
    public HttpStatus addREviews(@RequestBody Review review){
        reviewService.addReview(review);
        return HttpStatus.OK;
    }
}
