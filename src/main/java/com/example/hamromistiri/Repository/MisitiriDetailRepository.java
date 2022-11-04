package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.MistiriDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MisitiriDetailRepository extends JpaRepository<MistiriDetail,Integer> {

    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            "inner join customer c on u.customer_id = c.id\n" +
            "where c.location = ?2\n" +
            "  and u.available_status = true\n" +
            "  and c.is_verified = true\n" +
            "  and u.service = ?1\n" +
            "order by u.rating desc")
    List<MistiriDetail> findAvailableMistiri(String service, String address);


    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            "         inner join customer c on u.customer_id = c.id\n" +
            "where not c.location = ?2\n" +
            "  and u.available_status = true\n" +
            "  and u.service = ?1\n" +
            "order by u.rating desc")
    List<MistiriDetail> findNotByAddress(String service, String address);


    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            "inner join customer c on u.customer_id = c.id\n" +
            " where u.available_status = true\n" +
            "  and c.is_verified = true\n" +
            "  and u.service = ?1\n" +
            "order by u.rating desc")
    List<MistiriDetail> findByServices(String service);


    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            "where u.mistiri_id = ?1\n")
    MistiriDetail findAvailableMistiriId(Integer id);


    @Modifying
    @Query(nativeQuery = true, value = "delete \n" +
            "from mistiri_detail u\n" +
            "where u.customer_id = ?1\n")
    void deleteMistiriByCustomerId(int uid);


    @Query(nativeQuery = true, value = "select *\n" +
            "from mistiri_detail u\n" +
            " where u.customer_id = ?1\n")
    MistiriDetail findByCustomerId(int customerId);


}



