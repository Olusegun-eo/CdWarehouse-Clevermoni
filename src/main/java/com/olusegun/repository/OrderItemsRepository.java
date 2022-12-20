package com.olusegun.repository;

import com.olusegun.data.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
