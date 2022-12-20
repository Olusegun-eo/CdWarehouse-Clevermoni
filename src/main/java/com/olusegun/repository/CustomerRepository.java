package com.olusegun.repository;

import com.olusegun.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    Customer findByEmail(String email);

    Customer findUserByEmail(String email);
}
