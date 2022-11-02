package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Converter.EntityToDtoConverter;
import com.example.hamromistiri.Dto.*;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Service.MistiriDetailsService;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.ok(new ApiResponse(customer,"Logged in successfully"));
    }





    @GetMapping("/verify/mistiri/{id}/{token}")
    public String verifyCustomer(@PathVariable int id,
                                 @PathVariable String token){
        return mistiriDetailsService.verify(id,token);

    }

    @PutMapping("/updateMistiri")
    public String updateMistiriData(@RequestBody MistiriDetail mistiri){
        mistiriDetailsService.updateMistiri(mistiri);
        return "Your profile is updated successfully.";
    }

    @GetMapping("/mistiriDashboard/{id}")
    public Optional<MistiriDetail> getMistiri(@PathVariable int id){
        return mistiriDetailsService.getMistiri(id);
    }



//    @PostMapping("/mistiri/addMistiri")
//    public HttpStatus addMistiri(@Valid @RequestBody MistriAddDto mistiriDetail){
//        mistiriDetailsService.addMistiri(converter.DtoToEntity(mistiriDetail));
//        return HttpStatus.OK;
//    }


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
