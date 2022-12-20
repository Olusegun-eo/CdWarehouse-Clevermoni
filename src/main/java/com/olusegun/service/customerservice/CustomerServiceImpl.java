package com.olusegun.service.customerservice;


import com.olusegun.config.MessageResponse;
import com.olusegun.data.dto.*;
import com.olusegun.data.model.AuthenticationToken;
import com.olusegun.data.model.Customer;
import com.olusegun.data.model.ResponseStatus;
import com.olusegun.data.model.Role;
import com.olusegun.exceptions.AuthenticationFailException;
import com.olusegun.exceptions.CustomException;
import com.olusegun.repository.CustomerRepository;
import com.olusegun.service.AuthenticationService;
import com.olusegun.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.olusegun.config.MessageResponse.CUSTOMER_CREATED;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerResponseDto signUp(SignUpDto signupDto)  throws CustomException {
        if (Helper.notNull(customerRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("Customer already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        Customer customer = new Customer(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), Role.CUSTOMER, encryptedPassword );
        Customer newCustomer;
        try {
            newCustomer = customerRepository.save(customer);
            final AuthenticationToken authenticationToken = new AuthenticationToken(newCustomer);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new CustomerResponseDto(ResponseStatus.SUCCESS.toString(), CUSTOMER_CREATED);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        Customer customer = customerRepository.findByEmail(signInDto.getEmail());
        if(!Helper.notNull(customer)){
            throw  new AuthenticationFailException("Customer not present");
        }
        try {
            if (!customer.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw  new AuthenticationFailException(MessageResponse.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error(" password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(customer);

        if(!Helper.notNull(token)) {
            // token not present
            throw new CustomException("token not present");
        }

        return new SignInResponseDto ("success", token.getToken());
    }


    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }



    public CustomerResponseDto createUser(String token, CustomerRequestDto customerRequestDto) throws CustomException, AuthenticationFailException {
        Customer customer = authenticationService.getUser(token);
        if (!customerRole(customer.getRole())) {
            // user can't create new user
            throw  new AuthenticationFailException(MessageResponse.CUSTOMER_NOT_PERMITTED);
        }
        String encryptedPassword = customerRequestDto.getPassword();
        try {
            encryptedPassword = hashPassword(customerRequestDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error(" password failed {}", e.getMessage());
        }

        Customer createCustomer = new Customer(customerRequestDto.getFirstName(),
                customerRequestDto.getLastName(),
                customerRequestDto.getEmail(),
                customerRequestDto.getRole(),
                encryptedPassword );

            Customer newCustomer;
        try
            {
                newCustomer = customerRepository.save(createCustomer);
                final AuthenticationToken authenticationToken = new AuthenticationToken(newCustomer);
                authenticationService.saveConfirmationToken(authenticationToken);
                return new CustomerResponseDto(ResponseStatus.SUCCESS.toString(), CUSTOMER_CREATED);
            } catch(
            Exception e)

            {
                // handle user creation fail error
                throw new CustomException(e.getMessage());
            }
        }

    boolean customerRole(Role role) {
        if (role == Role.ADMIN || role == Role.SUPERVISOR) {
            return true;
        }
        return false;
    }

        boolean updateCustomer(Customer updateCustomer, Long customerId) {
        Role role = updateCustomer.getRole();
        // admin and manager can crud any user
        if (role == Role.ADMIN || role == Role.SUPERVISOR) {
            return true;
        }
        // user can update his own record, but not his role
        if (role == Role.CUSTOMER && updateCustomer.getId() == customerId) {
            return true;
        }
        return false;
    }

}
