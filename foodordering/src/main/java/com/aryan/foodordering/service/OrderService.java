package com.aryan.foodordering.service;

import com.aryan.foodordering.model.Cart;
import com.aryan.foodordering.model.Order;
import com.aryan.foodordering.model.OrderItem;
import com.aryan.foodordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = new Order();

        order.setUser(cart.getUser());
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setFoodItem(cartItem.getFoodItem());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPriceAtOrderTime(cartItem.getFoodItem().getPrice());
                    return orderItem;
                }).toList();

        order.setItems(orderItems);
        order.setTotalPrice(calculateTotal(orderItems));

        cartService.clearCart(cart.getId());
        return orderRepository.save(order);
    }

    private double calculateTotal(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getPriceAtOrderTime() * item.getQuantity())
                .sum();
    }
}