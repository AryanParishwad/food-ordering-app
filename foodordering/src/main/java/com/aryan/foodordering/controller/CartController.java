package com.aryan.foodordering.controller;

import com.aryan.foodordering.model.Cart;
import com.aryan.foodordering.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping
    public Cart saveCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @DeleteMapping("/{cartId}")
    public void clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
    }
}
