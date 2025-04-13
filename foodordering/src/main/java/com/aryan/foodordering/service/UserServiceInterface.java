package com.aryan.foodordering.service;

import com.aryan.foodordering.model.User;
import java.util.Optional;

public interface UserServiceInterface {
    User saveUser(User user);
    Optional<User> findByEmail(String email);
}
