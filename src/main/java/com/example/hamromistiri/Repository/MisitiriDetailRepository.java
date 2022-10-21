package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MisitiriDetailRepository extends JpaRepository<MistiriDetail,Integer>{

     @Query(nativeQuery = true, value = "select *\n" +
             "from mistiri_detail u\n" +
             "inner join customer c on u.customer_id = c.id\n" +
             "where c.location = ?1\n" +
             "  and u.available_status = true\n" +
             "  and u.service = ?2\n" +
             "order by u.rating desc")
     List<MistiriDetail> findAvailableMistiri(String address, String service);


    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            "         inner join customer c on u.customer_id = c.id\n" +
            "where not c.location = ?1\n" +
            "  and u.available_status = true\n" +
            "  and u.service = ?2\n" +
            "order by u.rating desc")
    List<MistiriDetail> findNotByAddress( String address, String service);


    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            "  where u.available_status = true\n" +
            "  and u.service = ?1\n" +
            "order by u.rating desc")
    List<MistiriDetail> findByServices(String service);

}
