package com.olusegun.data.dto;

import com.olusegun.data.model.Order;
import org.jetbrains.annotations.NotNull;

public class OrderDto {
    private Long id;
    private @NotNull
    Integer userId;

    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.setId(order.getId());
        //this.setUserId(order.getUserId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}