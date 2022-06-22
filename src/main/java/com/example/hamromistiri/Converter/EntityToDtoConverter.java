package com.example.hamromistiri.Converter;

import com.example.hamromistiri.Dto.MistiriDto;
import com.example.hamromistiri.Dto.ReviewDto;
import com.example.hamromistiri.Dto.ShowReviewDto;
import com.example.hamromistiri.Dto.UserDto;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDtoConverter {

    public UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public MistiriDto entityToDto(MistiriDetail mistiriDetail){
        MistiriDto mistiriDto = new MistiriDto();
        mistiriDto.setMistiriId(mistiriDetail.getId());
        mistiriDto.setAddress(mistiriDetail.getAddress());
        mistiriDto.setUser(entityToDto(mistiriDetail.getUser()));
        mistiriDto.setAboutYou(mistiriDetail.getAboutYou());
        mistiriDto.setRating(mistiriDetail.getRating());
        mistiriDto.setService(mistiriDetail.getService());
        mistiriDto.setAvailableStatus(mistiriDetail.getAvailableStatus());
        mistiriDto.setEmployeeStatus(mistiriDetail.getEmployeeStatus());
        return mistiriDto;
    }

     public List<MistiriDto> entityToDto(List<MistiriDetail> mistiriDetails){
        return mistiriDetails.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
     }

     public ReviewDto entityToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setComment(review.getComment());
        reviewDto.setUser(entityToDto(review.getUser()));
        return reviewDto;
     }

//     public ShowReviewDto mistiriEntityToDto(MistiriDetail mistiriDetail){
//        ShowReviewDto showReviewDto = new ShowReviewDto();
//        showReviewDto.setService(mistiriDetail.getService());
//        showReviewDto.setRating(mistiriDetail.getRating());
//        showReviewDto.setReview(mistiriDetail.getReview().stream().map(x -> entityToDto(x)).collect(Collectors.toList()));
//        return  showReviewDto;
//     }

}
