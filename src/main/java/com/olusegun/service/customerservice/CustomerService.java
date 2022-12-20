package com.olusegun.service.customerservice;


import com.olusegun.data.dto.*;
import com.olusegun.data.model.Customer;
import com.olusegun.exceptions.AuthenticationFailException;
import com.olusegun.exceptions.CustomException;

public interface CustomerService {

       CustomerResponseDto createUser(String token, CustomerRequestDto customerRequestDto) throws CustomException, AuthenticationFailException;
       CustomerResponseDto signUp(SignUpDto signupDto)  throws CustomException;
       SignInResponseDto signIn(SignInDto signInDto) throws CustomException;
//      boolean updateCustomer(Customer updateCustomer, Long customerId) ;

}
