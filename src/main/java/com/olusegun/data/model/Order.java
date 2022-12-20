package com.olusegun.data.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="orders")
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "created_date")
        private Date createdDate;

        @Column(name = "total_price")
        private Double totalPrice;

        @Column(name = "session_id")
        private String sessionId;

        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
        private List<OrderItem> orderItems;

        @ManyToOne()
        @JsonIgnore
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private Customer customer;

        public Order() {
        }


        public List<OrderItem> getOrderItems() {
                return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
                this.orderItems = orderItems;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }


        public Date getCreatedDate() {
                return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
                this.createdDate = createdDate;
        }

        public Double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
        }

        public String getSessionId() {
                return sessionId;
        }

        public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
        }

        public Customer getUser() {
                return customer;
        }

        public void setUser(Customer customer) {
                this.customer = customer;
        }
}