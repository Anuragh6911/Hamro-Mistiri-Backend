package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Converter.EntityToDtoConverter;
import com.example.hamromistiri.Dto.MistiriDto;
import com.example.hamromistiri.Dto.ShowReviewDto;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Service.MistiriDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MistiriDetailController {
    @Autowired
    private MistiriDetailsService mistiriDetailsService;

    @Autowired
    private EntityToDtoConverter  converter;

    @PostMapping("/mistiri/addMistiri")
    public HttpStatus addMistiri(@Valid @RequestBody  MistiriDetail mistiriDetail){
        mistiriDetailsService.addMistiri(mistiriDetail);
        return HttpStatus.OK;
    }

    @GetMapping("/mistiris")
    public List<MistiriDetail> findall(){
       return mistiriDetailsService.findAll();
   }

   @GetMapping("/mistiris/{address}")
    public List<MistiriDto> findAllByAddress(@PathVariable String address){
        List <MistiriDetail> mistiriDetails = mistiriDetailsService.findByMistiri(address);
        return converter.entityToDto(mistiriDetails);
   }


   @PostMapping("/mistiri/{id}/addReview")
    public MistiriDetail addReview( @Valid @RequestBody MistiriDetail mistiriDetail , @PathVariable int id){
        return mistiriDetailsService.addMistiriReview(mistiriDetail,id);
   }

   @GetMapping("/mistiri/{id}/getReview")
    public ShowReviewDto mistiriReview(@PathVariable int id){
        return converter.mistiriEntityToDto(mistiriDetailsService.showReview(id));
   }

}
