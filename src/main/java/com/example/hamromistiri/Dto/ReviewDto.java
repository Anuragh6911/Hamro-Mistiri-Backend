package com.example.hamromistiri.Dto;

import com.example.hamromistiri.Model.MistiriDetail;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {
     private int id;

     @NotEmpty(message = "Comment is required")
     private String comment;

     @NotNull(message = "Individual rating  cannot be empty")
     private Double indivisualRating;

     @NotNull(message = "please enter user details")
     @Valid
     private CustomerDto user;

     private MistiriDetail mistiriDetail;
}

