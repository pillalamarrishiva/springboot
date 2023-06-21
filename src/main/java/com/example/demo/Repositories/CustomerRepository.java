package com.example.demo.Repositories;

import com.example.demo.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "SELECT * FROM Customer where customer_id = :id",nativeQuery=true)
    Customer findByCustomerId(Integer id);

}
