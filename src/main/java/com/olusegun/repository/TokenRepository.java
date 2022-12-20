package com.olusegun.repository;

import com.olusegun.data.model.AuthenticationToken;
import com.olusegun.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findTokenByUser(Customer customer);
    AuthenticationToken findTokenByToken(String token);
}
