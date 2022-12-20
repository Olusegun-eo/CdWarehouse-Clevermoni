package com.olusegun.service.cartservice;

import com.olusegun.data.dto.AddToCartDto;
import com.olusegun.data.dto.CartDto;
import com.olusegun.data.dto.CartItemDto;
import com.olusegun.data.model.Cart;
import com.olusegun.data.model.Cd;
import com.olusegun.data.model.Customer;
import com.olusegun.exceptions.CartItemNotExistException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CartService {

     void addToCart(AddToCartDto addToCartDto, Cd cd, Customer customer);
     CartDto listCartItems(Customer customer);
     void updateCartItem(AddToCartDto cartDto, Customer customer, Cd cd);
     void deleteCartItem(Long id, Long userId) throws CartItemNotExistException;
     void deleteCartItems(Long userId);
     void deleteUserCartItems(Customer customer);
}
