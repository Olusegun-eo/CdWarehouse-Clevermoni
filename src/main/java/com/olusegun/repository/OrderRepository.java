package com.olusegun.repository;

import com.olusegun.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
    List<Order> findAllByCdArtist(Integer userId);
    List<Order> findAllByCdTitle(Integer userId);

}
