package com.example.hamromistiri.Dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    @Min(value=1, message="Id must be given for user")
    int id;

    @NotEmpty(message = "FirstName cannot be null")
    private String firstName;

    @NotEmpty(message = "LastName cannot be null")
    private String lastName;

}
