package com.olusegun.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Cd cd;

    private Integer quantityAddedCart;

    public Stock(Cd cd, Integer quantityAddedCart) {
        if( quantityAddedCart <= cd.getQuantity()){
            this.quantityAddedCart = quantityAddedCart;
        }
        this.cd = cd;
    }

//    public void setQuantityAddedCart(Integer quantityAddedCart) {
//        if(quantityAddedCart <= product.getQuantity())
//            this.quantityAddedCart = quantityAddedCart;
//    }

    public void setQuantityAddedCart(Integer quantityAddedCart) {
        if (cd.getQuantity() >= quantityAddedCart) {
            this.quantityAddedCart = quantityAddedCart;
        }else this.quantityAddedCart = 0;
    }

}
