package com.example.hamromistiri.Service;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Review;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
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

    public MistiriDetail addMistiri(MistiriDetail mistiriDetail){
      return   misitiriDetailRepository.save(mistiriDetail);
    }

    public List<MistiriDetail> findByMistiri(String address) {
        List<MistiriDetail> mistiriDetails = new ArrayList<>();
         mistiriDetails.addAll( misitiriDetailRepository.findAvailableMistiri(address));
         mistiriDetails.addAll(misitiriDetailRepository.findNotByAddress(address));
        for(MistiriDetail mistiriDetail1:mistiriDetails){
            List<Review >review = mistiriDetail1.getReview();
            int individualRating=0;
            int count = 0;
            for (Review singleReview: review
                 ) {
                 if(singleReview.getIndivisualRating()==1){
                     individualRating = individualRating+10;
                 }else if (singleReview.getIndivisualRating()==2){
                     individualRating=individualRating+20;
                 }else if (singleReview.getIndivisualRating()==3){
                     individualRating=individualRating+30;
                 }else if (singleReview.getIndivisualRating()==4){
                     individualRating=individualRating+40;
                 }else if (singleReview.getIndivisualRating()==5){
                     individualRating=individualRating+50;
                 }else{
                     individualRating = individualRating;
                 }
                 count++;
            }
            int totalRating = individualRating/count;
            if(totalRating>0&&totalRating<=10){
                mistiriDetail1.setRating(1);
            }else if(totalRating>10&&totalRating<=20){
                mistiriDetail1.setRating(2);
            }else if(totalRating>20&&totalRating<=30){
                mistiriDetail1.setRating(3);
            }else if(totalRating>30&&totalRating<=40){
                mistiriDetail1.setRating(4);
            }else if(totalRating>40&&totalRating<=50){
                mistiriDetail1.setRating(2);
            }else{
                mistiriDetail1.setRating(0);
            }
        }
        return mistiriDetails;
    }

    public MistiriDetail addMistiriReview(MistiriDetail mistiriDetail, int mistiriId){
          MistiriDetail existindMistiri = misitiriDetailRepository.findById(mistiriId).orElse(null);
          List<Review> reviews = existindMistiri.getReview();
          reviews.addAll(mistiriDetail.getReview());
          return misitiriDetailRepository.save(existindMistiri);
    }

    public MistiriDetail showReview(int id){
        return misitiriDetailRepository.findById(id).orElse(null); //ya exception haandelling garna baki cha !!!!!!!!!!
    }

}

