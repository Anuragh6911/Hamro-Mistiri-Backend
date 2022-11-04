package com.example.hamromistiri.Service;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.Repository.ReviewRepository;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MistiriDetailsService mistiriDetailsService;

    @Autowired
    private MisitiriDetailRepository misitiriDetailRepository;

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
        }else if(count >0){
            double rating = mistiriDetail.getRating();
            double ratingPoints = rating*count*10;
            count++;
            double individaulRatingPoints = 0;
            double individualRating = review.getIndivisualRating();
             if(individualRating == 1.0){
                 individaulRatingPoints = 10;
             }else if(individualRating == 2.0){
                 individaulRatingPoints = 20;
             }else if(individualRating == 3.0){
                individaulRatingPoints = 30;
            }else if(individualRating == 4.0){
                individaulRatingPoints = 40;
            } else if(individualRating == 5.0){
                individaulRatingPoints = 50;
            }
              double newRating= 0.0d;
              double totalRating = individaulRatingPoints+ratingPoints;
              double divideByCount = totalRating/count;
              newRating = divideByCount/10;
              mistiriDetail.setRating(newRating);
        }
        mistiriDetail.setCount(count);
        misitiriDetailRepository.save(mistiriDetail);
        review1.setMistiriDetail(mistiriDetail);
        review1.setComment(review.getComment());
        review1.setIndivisualRating(review.getIndivisualRating());
        review1.setCustomer(review.getCustomer());
        return reviewRepository.save(review1);
    }

    public List<Review> showReviews(int id){
       return reviewRepository.findReviewFromMistiriId(id);
    }

    public void deleteReview(int id) throws AppException {
        reviewRepository.deleteReviewByCustomerId(id);
    }
}
