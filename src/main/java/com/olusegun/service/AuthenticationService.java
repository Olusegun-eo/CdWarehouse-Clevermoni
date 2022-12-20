package com.olusegun.service;


import com.olusegun.config.MessageResponse;
import com.olusegun.data.model.AuthenticationToken;
import com.olusegun.data.model.Customer;
import com.olusegun.exceptions.AuthenticationFailException;
import com.olusegun.repository.TokenRepository;
import com.olusegun.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository repository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    public AuthenticationToken getToken(Customer customer) {
        return repository.findTokenByUser(customer);
    }

    public Customer getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getCustomer())){
                return authenticationToken.getCustomer();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageResponse.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(MessageResponse.AUTH_TOEKN_NOT_VALID);
        }
    }
}
