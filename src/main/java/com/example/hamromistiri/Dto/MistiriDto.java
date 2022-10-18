package com.example.hamromistiri.Dto;

import lombok.Data;

@Data
public class MistiriDto {
       private  int mistiriId;
       private CustomerDto user;
       private String address;
       private String service;
       private String aboutYou;
       private Boolean availableStatus;
       private String employeeStatus;
       private double rating;
}
