package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MisitiriDetailRepository extends JpaRepository<MistiriDetail,Integer>{

    @Query("select u from MistiriDetail u where u.address = :address and u.availableStatus=true")
     List<MistiriDetail> findAvailableMistiri(String address);


    @Query("select u from MistiriDetail u where not u.address = :address and u.availableStatus=true")
    List<MistiriDetail> findNotByAddress(@Param("address") String address);

}
