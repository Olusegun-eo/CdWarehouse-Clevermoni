package com.olusegun.controller;

import com.olusegun.data.dto.*;
import com.olusegun.data.model.Customer;
import com.olusegun.exceptions.AuthenticationFailException;
import com.olusegun.exceptions.CustomException;
import com.olusegun.repository.CustomerRepository;
import com.olusegun.service.AuthenticationService;
import com.olusegun.service.customerservice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

        @Autowired
        CustomerRepository customerRepository;

        @Autowired
        AuthenticationService authenticationService;

        @Autowired
        CustomerService customerService;

        @GetMapping("/all_customers")
        public List<Customer> findAllCustomers(@RequestParam("token") String token) throws AuthenticationFailException {
            authenticationService.authenticate(token);
            return customerRepository.findAll();
        }

    @PostMapping("/createUser")
    public CustomerResponseDto updateUser(@RequestParam("token") String token, @RequestBody CustomerRequestDto customerRequestDto)
            throws CustomException, AuthenticationFailException {
        authenticationService.authenticate(token);
        return customerService.createUser(token, customerRequestDto);
    }

    @PostMapping("/signup")
        public CustomerResponseDto Signup(@RequestBody SignUpDto signUpDto) throws CustomException {
            return customerService.signUp(signUpDto);
        }

        //TODO token should be updated
        @PostMapping("/signIn")
        public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
            return customerService.signIn(signInDto);
        }

//    @PostMapping("/updateUser")
//    public CustomerResponseDto updateUser(@RequestParam("token") String token, @RequestBody UserUpdateDto userUpdateDto) {
//        authenticationService.authenticate(token);
//        return customerService.updateCustomer(token, userUpdateDto);
//    }




}
