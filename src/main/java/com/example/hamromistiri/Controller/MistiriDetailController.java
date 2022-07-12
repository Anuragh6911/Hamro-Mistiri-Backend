package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Converter.EntityToDtoConverter;
import com.example.hamromistiri.Dto.MistiriDto;
import com.example.hamromistiri.Dto.MistriAddDto;
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
    public HttpStatus addMistiri(@Valid @RequestBody MistriAddDto mistiriDetail){
        mistiriDetailsService.addMistiri(converter.DtoToEntity(mistiriDetail));
        return HttpStatus.OK;
    }

    @GetMapping("/mistiris")
    public List<MistiriDetail> findall(){
       return mistiriDetailsService.findAll();
   }

   @GetMapping("/mistiris/{services}/{address}")
    public List<MistiriDto> findAllByAddress(@PathVariable String address , @PathVariable String services){
        List <MistiriDetail> mistiriDetails = mistiriDetailsService.findByMistiri(address,services);
        return converter.entityToDto(mistiriDetails);
   }
}
