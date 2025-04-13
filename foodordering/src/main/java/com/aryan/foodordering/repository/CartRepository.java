package com.aryan.foodordering.repository;

import com.aryan.foodordering.model.Cart;
import com.aryan.foodordering.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
