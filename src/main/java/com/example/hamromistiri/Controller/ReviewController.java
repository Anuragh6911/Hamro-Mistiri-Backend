package com.example.hamromistiri.Controller;

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

    @PostMapping("/{id}/addReview")
    public HttpStatus addREviews(@PathVariable int id, @RequestBody Review review){
        reviewService.addReview(id,review);
        return HttpStatus.OK;
    }

    @GetMapping("/findReviews/{id}")
    public List<Review> findALlReview(@PathVariable int id){
        return reviewService.showReviews(id);
    }
}
