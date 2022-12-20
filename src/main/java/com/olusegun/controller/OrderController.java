package com.olusegun.controller;

import com.olusegun.api.ApiResponse;
import com.olusegun.data.model.Customer;
import com.olusegun.data.model.Order;
import com.olusegun.exceptions.AuthenticationFailException;
import com.olusegun.exceptions.OrderNotFoundException;
import com.olusegun.service.AuthenticationService;
import com.olusegun.service.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.webtutsplus.ecommerce.common.ApiResponse;
import com.webtutsplus.ecommerce.dto.checkout.CheckoutItemDto;
import com.webtutsplus.ecommerce.dto.checkout.StripeResponse;
import com.webtutsplus.ecommerce.exceptions.AuthenticationFailException;
import com.webtutsplus.ecommerce.exceptions.OrderNotFoundException;
import com.webtutsplus.ecommerce.model.Order;
import com.webtutsplus.ecommerce.model.User;
import com.webtutsplus.ecommerce.service.AuthenticationService;
import com.webtutsplus.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;


    // stripe create session API
    @PostMapping("/create-checkout-session")
    public ResponseEntity<CollectPaymentPayload> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        // create the stripe session

    }

    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        Customer customer = authenticationService.getUser(token);
        // place the order
        orderService.placeRecordOrder(customer, sessionId);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        Customer customer = authenticationService.getUser(token);
        List<Order> orderDtoList = orderService.listOrders(customer);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id, @RequestParam("token") String token)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        try {
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}