package com.example.hamromistiri.Dto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class MistriAddDto {
     private int id;

     @Valid
     private CustomerValidationDto user;

     @NotEmpty(message = "Pan Number cannot be empty")
     @Length( min = 9,max = 9, message = "Pan Number should be of 9 digits")
     @Pattern(regexp = "^[1-9]+[0-9]*$" ,message = "Only number is allowed in pan number")
     private  String panNo;

     private double rating;

     @NotEmpty(message = "Service field cannot be empty")
     private String service;

     private Boolean availableStatus;

     @NotEmpty(message = "Employee Status cannot be empty")
     private String employeeStatus;

     @NotEmpty(message = "Document cannot be empty")
     private  String documents;

     private String aboutYou;

     @NotEmpty(message = "address cannot be empty")
     private String address;

     private int count;

}
