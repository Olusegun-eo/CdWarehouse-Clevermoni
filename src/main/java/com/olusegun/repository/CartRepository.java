package com.olusegun.repository;

import com.olusegun.data.model.Cart;
import com.olusegun.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(Customer customer);
    List<Cart> deleteByUser(Customer customer);
}
