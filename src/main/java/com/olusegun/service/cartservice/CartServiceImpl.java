package com.olusegun.service.cartservice;

import com.olusegun.data.dto.AddToCartDto;
import com.olusegun.data.dto.CartDto;
import com.olusegun.data.dto.CartItemDto;
import com.olusegun.data.model.Cart;
import com.olusegun.data.model.Cd;
import com.olusegun.data.model.Customer;
import com.olusegun.exceptions.CartItemNotExistException;
import com.olusegun.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl(){}

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Cd cd, Customer customer){
        Cart cart = new Cart(cd, addToCartDto.getQuantity(), customer);
        cartRepository.save(cart);
    }


    public CartDto listCartItems(Customer customer) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(customer);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getCd().getCdPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems, totalCost);
    }


    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }


    public void updateCartItem(AddToCartDto cartDto, Customer customer, Cd cd){
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(Long userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(Customer customer) {
        cartRepository.deleteByUser(customer);
    }

}
