package com.object.mapper.repository;

import com.object.mapper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    Customer getCustomerByCustomerId(int id);
}
