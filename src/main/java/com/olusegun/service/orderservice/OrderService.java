package com.olusegun.service.orderservice;


import com.olusegun.data.dto.PlaceOrderDto;
import com.olusegun.data.model.Customer;
import com.olusegun.data.model.Order;
import com.olusegun.exceptions.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    void placeOrder(Long customerId, String sessionId);
    void placeRecordOrder(Customer customer, String sessionId);

    int saveOrder(PlaceOrderDto orderDto, int userId, String sessionID);

    public Order getOrderFromDto(PlaceOrderDto orderDto, int userId, String sessionID);

    List<Order> listOrders(Customer customer);

    public Order getOrder(Long orderId) throws OrderNotFoundException
}
