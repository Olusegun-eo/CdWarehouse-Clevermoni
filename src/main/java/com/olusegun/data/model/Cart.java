package com.olusegun.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="cart")
public class Cart {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "created_date")
        private Date createdDate;

        @ManyToOne
        @JoinColumn(name = "cd_id", referencedColumnName = "id")
        private Cd cd;

        @JsonIgnore
        @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "customer_id")
        private Customer customer;

        private int quantity;

        public Cart() {
        }

        public Cart(Cd cd, int quantity, Customer customer){
            this.customer = customer;
            this.cd = cd;
            this.quantity = quantity;
            this.createdDate = new Date();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public Cd getCd() {
            return cd;
        }

        public void setCd(Cd cd) {
            this.cd = cd;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
}
