package com.example.hamromistiri.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MistiriSignupRequest {

    @NotEmpty(message = "FirstName cannot be null")
    private String firstName;

    @NotEmpty(message = "LastName cannot be null")
    private String lastName;

    @NotEmpty(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "pattern didn't matched for email")
    private String email;

    @NotEmpty(message = "Password cannot be null")
    private String password;

    private String role;

    @NotEmpty(message = "PhoneNo cannot be null")
    @Length(min = 10, max = 10, message = "Phone number needs to be of 10 digits")
    @Pattern(regexp = "^[1-9]+[0-9]*$" ,message = "Only number is allowed in phoneNo")
    private String phoneNo;

    @NotEmpty(message = "address cannot be empty")
    private String location;

    @NotEmpty(message = "Pan Number cannot be empty")
    @Length( min = 9,max = 9, message = "Pan Number should be of 9 digits")
    @Pattern(regexp = "^[1-9]+[0-9]*$" ,message = "Only number is allowed in pan number")
    private String panNo;

    private Double rating;

    @NotEmpty(message = "Service field cannot be empty")
    private String service;


    private Boolean availableStatus;

    @NotEmpty(message = "Employee Status cannot be empty")
    private String employeeStatus;

    private MultipartFile document;

    private String aboutYou;

    private Integer count;
}
