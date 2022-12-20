package com.olusegun.data.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    public AuthenticationToken() {
    }

    public AuthenticationToken(Customer custom) {
        this.customer = customer;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public AuthenticationToken(Long id, String Token, Date createdDate, Customer customer) {
        this.id = id;
        this.token = Token;
        this.createdDate = createdDate;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String Token) {
        this.token = Token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

}
