package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Converter.EntityToDtoConverter;
import com.example.hamromistiri.Dto.ReviewDto;
import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ReviewController {

   @Autowired
   private ReviewService reviewService;

    @Autowired
    private EntityToDtoConverter converter;

    @GetMapping("/findAllReviews")
    public List<Review> findByID(){
        return reviewService.reviews();
    }

    @PostMapping("/rateMe/{id}")
    public HttpStatus addReviews(@PathVariable int id,  @Valid @RequestBody ReviewDto review){
        reviewService.addReview(id,converter.DtoToEntity(review));
        return HttpStatus.OK;
    }

    @GetMapping("/findReviews/{id}")
    public List<Review> findALlReview(@PathVariable int id){
        return reviewService.showReviews(id);
    }
}
