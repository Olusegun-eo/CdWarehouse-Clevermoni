package com.olusegun.controller;

import com.olusegun.api.ApiResponse;
import com.olusegun.data.dto.AddToCartDto;
import com.olusegun.data.dto.CartDto;
import com.olusegun.data.model.Cd;
import com.olusegun.data.model.Customer;
import com.olusegun.exceptions.AuthenticationFailException;
import com.olusegun.exceptions.CartItemNotExistException;
import com.olusegun.exceptions.CdNotExistException;
import com.olusegun.service.AuthenticationService;
import com.olusegun.service.cartservice.CartService;
import com.olusegun.service.cdservice.CdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

        @Autowired
        private CartService cartService;

        @Autowired
        private CdService cdService;

        @Autowired
        private AuthenticationService authenticationService;

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                     @RequestParam("token") String token) throws AuthenticationFailException, CdNotExistException {
            authenticationService.authenticate(token);
            Customer customer = authenticationService.getUser(token);
            Cd cd = cdService.getCdById(addToCartDto.getCdId());
            System.out.println("product to add"+  cd.getCdTitle());
            cartService.addToCart(addToCartDto, cd, customer);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

        }
        @GetMapping("/")
        public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
            authenticationService.authenticate(token);
            Customer user = authenticationService.getUser(token);
            CartDto cartDto = cartService.listCartItems(user);
            return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
        }
        @PutMapping("/update/{cartItemId}")
        public ResponseEntity<ApiResponse> updateCartItem(@RequestBody AddToCartDto cartDto,
                                                          @RequestParam("token") String token) throws AuthenticationFailException, CdNotExistException {
            authenticationService.authenticate(token);
            Customer customer = authenticationService.getUser(token);
            Cd cd = cdService.getCdById(cartDto.getCdId());
            cartService.updateCartItem(cartDto, customer, cd);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{cartItemId}")
        public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long cdID, @RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
            authenticationService.authenticate(token);
            Long customerId = authenticationService.getUser(token).getId();
            cartService.deleteCartItem(cdID, customerId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
        }
}
