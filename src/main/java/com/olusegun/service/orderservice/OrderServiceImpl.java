package com.olusegun.service.orderservice;


import com.olusegun.data.dto.CartDto;
import com.olusegun.data.dto.CartItemDto;
import com.olusegun.data.dto.OrderDto;
import com.olusegun.data.model.Customer;
import com.olusegun.data.model.Order;
import com.olusegun.data.model.OrderItem;
import com.olusegun.exceptions.OrderNotFoundException;
import com.olusegun.repository.OrderItemsRepository;
import com.olusegun.repository.OrderRepository;
import com.olusegun.service.cartservice.CartService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    OrderItemsService orderItemsService;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    public void placeOrder(Long customerId, String sessionId) {
        CartDto cartDto = cartService.listCartItems(customerId);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUserId(userId);
        placeOrderDto.setTotalPrice(cartDto.getTotalCost());

        int orderId = saveOrder(placeOrderDto, userId, sessionId);
        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem(
                    orderId,
                    cartItemDto.getProduct().getId(),
                    cartItemDto.getQuantity(),
                    cartItemDto.getProduct().getPrice());
            orderItemsService.addOrderedProducts(orderItem);
        }
        cartService.deleteCartItems(userId);
    }



    public void placeRecordOrder(Customer customer, String sessionId) {
        // first let get cart items for the user
        CartDto cartDto = cartService.listCartItems(customer);

        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setSessionId(sessionId);
        newOrder.setUser(customer);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save each one
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getCd().getCdPrice());
            orderItem.setCd(cartItemDto.getCd());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemsRepository.save(orderItem);
        }
        //
        cartService.deleteUserCartItems(customer);
    }

    public int saveOrder(OrderDto orderDto, Long userId, String sessionID){
        Order order = getOrderFromDto(orderDto, userId,sessionID);
        return orderRepository.save(order).getId();
    }

    public Order getOrderFromDto(OrderDto orderDto, int userId, String sessionID) {
        Order order = new Order(orderDto, userId, sessionID);
        return order;
    }

    public List<Order> listOrders(Customer customer) {
        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedDateDesc(customer);
        return orderList;
    }


    public Order getOrder(Long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }

}
