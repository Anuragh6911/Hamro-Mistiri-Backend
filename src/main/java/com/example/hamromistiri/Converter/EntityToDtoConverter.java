package com.example.hamromistiri.Converter;

import com.example.hamromistiri.Dto.*;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDtoConverter {

    public CustomerDto entityToDto(Customer customer){
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        return dto;
    }

    public Customer DtoToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        return customer;
    }

    public Customer DtoToEntity(CustomerValidationDto customerValidationDto){
        Customer customer = new Customer();
        customer.setId(customerValidationDto.getId());
        customer.setFirstName(customerValidationDto.getFirstName());
        customer.setLastName(customerValidationDto.getLastName());
        customer.setEmail(customerValidationDto.getEmail());
        customer.setPassword(customerValidationDto.getPassword());
        customer.setPhoneNo(customerValidationDto.getPhoneNo());
        customer.setRole(customerValidationDto.getRole());
        return customer;
    }

    public MistiriDetail DtoToEntity(MistriAddDto mistiriDetail){
        MistiriDetail mistiriDetail1 = new MistiriDetail();
        mistiriDetail1.setId(mistiriDetail.getId());
        mistiriDetail1.setCustomer(DtoToEntity(mistiriDetail.getUser()));
        mistiriDetail1.setCount(mistiriDetail.getCount());
        mistiriDetail1.setPanNo(mistiriDetail.getPanNo());
        mistiriDetail1.setDocuments(mistiriDetail.getDocuments());
        mistiriDetail1.setService(mistiriDetail.getService());
        mistiriDetail1.setEmployeeStatus(mistiriDetail.getEmployeeStatus());
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
        mistiriDto.setUser(entityToDto(mistiriDetail.getCustomer()));
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
        review.setCustomer(DtoToEntity(reviewDto.getCustomerDto()));
        review.setMistiriDetail(reviewDto.getMistiriDetail());
        review.setIndivisualRating(reviewDto.getIndivisualRating());
        return  review;
    }



}
