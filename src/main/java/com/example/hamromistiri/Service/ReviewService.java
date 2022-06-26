package com.example.hamromistiri.Service;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MistiriDetailsService mistiriDetailsService;

    public List<Review> reviews(){
        return reviewRepository.findAll();
    }

    public Review addReview(int id,Review review){
        Review review1 = new Review();
        MistiriDetail mistiriDetail = mistiriDetailsService.findById(id);
        int count = mistiriDetail.getCount();
        if(count == 0 ){
            count++;
            mistiriDetail.setRating(review.getIndivisualRating());
        }else{
            count++;
            int rating = mistiriDetail.getRating();
            int ratingPoints = rating*count*10;
            int individaulRatingPoints = 0;
            int individualRating = review.getIndivisualRating();
             if(individualRating == 1){
                 individaulRatingPoints = 10;
             }else if(individualRating == 2){
                 individaulRatingPoints = 20;
             }else if(individualRating == 3){
                individaulRatingPoints = 30;
            }else if(individualRating == 4){
                individaulRatingPoints = 40;
            } else if(individualRating == 5){
                individaulRatingPoints = 50;
            }

        }
        review1.setMistiriDetail(mistiriDetail);
        int rate = review.getIndivisualRating();
        return reviewRepository.save(review1);
    }

    public List<Review> showReviews(int id){
       return reviewRepository.findReviewFromMistiriId(id);
    }

}
