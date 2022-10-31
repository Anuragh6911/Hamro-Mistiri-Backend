package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Converter.EntityToDtoConverter;
import com.example.hamromistiri.Dto.MistiriDto;
import com.example.hamromistiri.Dto.MistiriSignupRequest;
import com.example.hamromistiri.Dto.MistriAddDto;
import com.example.hamromistiri.Dto.MistiriLoginRequest;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Service.MistiriDetailsService;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*") //.env file limited allowed origins
public class MistiriDetailController {
    @Autowired
    private MistiriDetailsService mistiriDetailsService;

    @Autowired
    private EntityToDtoConverter  converter;

    @PostMapping("/registerMistiri")
    public MistiriDetail signUpMistiri(@Valid @RequestBody MistiriSignupRequest request) throws AppException {
        return mistiriDetailsService.registerMistiri(request);
    }

    @PostMapping("/loginMistiri")
    public ResponseEntity<?> loginMistiri(@RequestBody MistiriLoginRequest request, HttpSession session) throws AppException {
        Customer customer = mistiriDetailsService.loginMistiri(request);
        session.setAttribute("id", customer.getId());
        session.setAttribute("email", customer.getEmail());
        session.setAttribute("firstName", customer.getFirstName());
        session.setAttribute("lastName", customer.getLastName());
        return ResponseEntity.ok("Logged in Successfully.");
    }


    @GetMapping("/mistiris")
    public List<MistiriDetail> findall(){
       return mistiriDetailsService.findAll();
   }

   @GetMapping("/mistiris/{services}/{address}")
    public List<MistiriDetail> findAllByAddress( @PathVariable String services, @PathVariable String address){
        List <MistiriDetail> mistiriDetails = mistiriDetailsService.findByMistiri(services,address);
        return mistiriDetails;
   }

   @GetMapping("/mistiris/{service}")
   public List<MistiriDetail> findByAddress(@PathVariable String service){
        List<MistiriDetail> mistiriDetails = mistiriDetailsService.findByService(service);
        return mistiriDetails;
   }
}
