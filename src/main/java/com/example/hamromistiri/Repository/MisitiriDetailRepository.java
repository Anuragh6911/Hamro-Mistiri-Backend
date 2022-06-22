package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MisitiriDetailRepository extends JpaRepository<MistiriDetail,Integer>{

     @Query("select u from MistiriDetail u where  u.address = :address and u.availableStatus=true and u.service= :service order by u.rating desc ")
     List<MistiriDetail> findAvailableMistiri(String address, String service);


    @Query("select u from MistiriDetail u where not u.address = :address and u.availableStatus=true and u.service = :service order by u.rating desc")
    List<MistiriDetail> findNotByAddress( String address, String service);

}
