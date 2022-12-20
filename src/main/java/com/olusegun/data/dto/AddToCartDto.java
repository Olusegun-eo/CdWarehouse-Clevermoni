package com.olusegun.data.dto;

import org.jetbrains.annotations.NotNull;

public class AddToCartDto {

    private Long id;
    private @NotNull Long cdId;
    private @NotNull Integer quantity;

    public AddToCartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long productId) {
        this.cdId = cdId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + cdId +
                ", quantity=" + quantity +
                ",";
    }
}

