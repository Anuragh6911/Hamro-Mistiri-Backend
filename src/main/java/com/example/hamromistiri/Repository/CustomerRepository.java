package com.example.hamromistiri.Repository;

import com.example.hamromistiri.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Optional<Customer> findByEmail(String email);

    Customer findById(int id);
}
