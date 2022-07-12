package com.example.hamromistiri.Converter;

import com.example.hamromistiri.Dto.*;
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
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public User DtoToEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public User DtoToEntity(UserValidationDto userValidationDto){
        User user = new User();
        user.setId(userValidationDto.getId());
        user.setFirstName(userValidationDto.getFirstName());
        user.setLastName(userValidationDto.getLastName());
        user.setEmail(userValidationDto.getEmail());
        user.setPassword(userValidationDto.getPassword());
        user.setPhoneNo(userValidationDto.getPhoneNo());
        user.setRole(userValidationDto.getRole());
        return user;
    }

    public MistiriDetail DtoToEntity(MistriAddDto mistiriDetail){
        MistiriDetail mistiriDetail1 = new MistiriDetail();
        mistiriDetail1.setId(mistiriDetail.getId());
        mistiriDetail1.setUser(DtoToEntity(mistiriDetail.getUser()));
        mistiriDetail1.setCount(mistiriDetail.getCount());
        mistiriDetail1.setPanNo(mistiriDetail.getPanNo());
        mistiriDetail1.setDocuments(mistiriDetail.getDocuments());
        mistiriDetail1.setService(mistiriDetail.getService());
        mistiriDetail1.setEmployeeStatus(mistiriDetail.getEmployeeStatus());
        mistiriDetail1.setAddress(mistiriDetail.getAddress());
        mistiriDetail1.setRating(mistiriDetail.getRating());
        mistiriDetail1.setCount(mistiriDetail.getCount());
        mistiriDetail1.setAboutYou(mistiriDetail.getAboutYou());
        return mistiriDetail1;
    }

     public List<MistiriDto> entityToDto(List<MistiriDetail> mistiriDetails){
        return mistiriDetails.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
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

    public Review DtoToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setComment(reviewDto.getComment());
        review.setUser(DtoToEntity(reviewDto.getUser()));
        review.setMistiriDetail(reviewDto.getMistiriDetail());
        return  review;
    }



}
