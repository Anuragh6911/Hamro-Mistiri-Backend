package com.example.hamromistiri.Service;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class MistiriDetailsService {
    @Autowired
    private MisitiriDetailRepository misitiriDetailRepository;

    public List <MistiriDetail> findAll(){
        return misitiriDetailRepository.findAll();
    }

    public MistiriDetail findById(int id){
        return  misitiriDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mistiri not found with id "+id));
    }

    public MistiriDetail addMistiri(MistiriDetail mistiriDetail){

        //this is for rating purpose!!! Don't edit this code.
        MistiriDetail mistiriDetail1 = new MistiriDetail();
        mistiriDetail1.setUser(mistiriDetail.getUser());
        mistiriDetail1.setRating(mistiriDetail.getRating());
        mistiriDetail1.setService(mistiriDetail.getService());
        mistiriDetail1.setAddress(mistiriDetail.getAddress());
        mistiriDetail1.setAboutYou(mistiriDetail.getAboutYou());
        mistiriDetail1.setDocuments(mistiriDetail.getDocuments());
        mistiriDetail1.setAvailableStatus(mistiriDetail.getAvailableStatus());
        mistiriDetail1.setEmployeeStatus(mistiriDetail.getEmployeeStatus());
        mistiriDetail1.setPanNo(mistiriDetail.getPanNo());
        mistiriDetail1.setCount(0);
        return   misitiriDetailRepository.save(mistiriDetail);
    }

    public List<MistiriDetail> findByMistiri(String address, String service) {
        List<MistiriDetail> mistiriDetails = new ArrayList<>();
        mistiriDetails.addAll( misitiriDetailRepository.findAvailableMistiri(address, service));
        mistiriDetails.addAll(misitiriDetailRepository.findNotByAddress(address, service));
        return mistiriDetails;
    }

    public MistiriDetail showReview(int id){
        return misitiriDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mistiri not found with id "+id));
    }

}