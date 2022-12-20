package com.olusegun.data.dto;

import com.olusegun.data.model.Cart;
import com.olusegun.data.model.Cd;
import org.jetbrains.annotations.NotNull;

public class CartItemDto {

    private Long id;
    private @NotNull Integer quantity;
    private @NotNull Cd cd;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setCd(cart.getCd());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", cdTitle=" + cd.getCdTitle() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Cd getCd() {
        return cd;
    }

    public void setCd(Cd cd) {
        this.cd = cd;
    }
}
