package com.example.hamromistiri.Dto;

import lombok.Data;

@Data
public class ReviewDto {
     private int id;
     private String comment;
     private UserDto user;
}
