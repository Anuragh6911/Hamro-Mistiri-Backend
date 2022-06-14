package com.example.hamromistiri.Dto;

import lombok.Data;

@Data
public class MistiriDto {
       private  int mistiriId;
       private UserDto user;
       private String address;
       private String service;
       private String aboutYou;
       private Boolean availableStatus;
       private String employeeStatus;
       private int rating;
}
