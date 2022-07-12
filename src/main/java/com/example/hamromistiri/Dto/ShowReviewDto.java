package com.example.hamromistiri.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ShowReviewDto {
    private String service;
    private double rating;
    private List<ReviewDto> review;
}

