package com.geekster.Restaurant.management.service.API.Controller;

import com.geekster.Restaurant.management.service.API.Model.Food;
import com.geekster.Restaurant.management.service.API.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    FoodService foodService;

    @GetMapping("food")
    public List<Food> getAllFoodItems()
    {
       return foodService.getAllFoodItems();
    }

}
