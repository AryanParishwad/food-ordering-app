package com.aryan.foodordering.controller;

import com.aryan.foodordering.model.FoodItem;
import com.aryan.foodordering.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public FoodItem addFood(@RequestBody FoodItem foodItem) {
        return foodItemService.addFoodItem(foodItem);
    }

    @GetMapping
    public List<FoodItem> getAllFood() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodById(@PathVariable Long id) {
        return foodItemService.getFoodItemById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
    }
}
