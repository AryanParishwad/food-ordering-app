package com.aryan.foodordering.service;

import com.aryan.foodordering.model.Cart;
import com.aryan.foodordering.model.CartItem;
import com.aryan.foodordering.model.FoodItem;
import com.aryan.foodordering.model.User;
import com.aryan.foodordering.repository.CartRepository;
import com.aryan.foodordering.repository.FoodItemRepository;
import com.aryan.foodordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository; // Add this

    @Autowired
    private FoodItemRepository foodItemRepository;

    public Cart getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void clearCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    // Add this new method
    public Cart addItemToCart(Long userId, Long foodItemId, int quantity) {
        Cart cart = getCartByUserId(userId);
        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        return cartRepository.save(cart);
    }
}
