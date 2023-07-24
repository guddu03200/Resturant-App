package com.geekster.Restaurant.management.service.API.Controller;

import com.geekster.Restaurant.management.service.API.Model.Food;
import com.geekster.Restaurant.management.service.API.Model.Order;
import com.geekster.Restaurant.management.service.API.Model.User;
import com.geekster.Restaurant.management.service.API.Model.dto.SignInInput;
import com.geekster.Restaurant.management.service.API.Model.dto.SignUpOutput;
import com.geekster.Restaurant.management.service.API.Service.AuthenticationService;
import com.geekster.Restaurant.management.service.API.Service.FoodService;
import com.geekster.Restaurant.management.service.API.Service.OrderService;
import com.geekster.Restaurant.management.service.API.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FoodService foodService;

    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PostMapping("user/SignIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }
    @PostMapping("order")
    public String addOrder(@RequestBody Order order, @RequestParam String email, @RequestParam String token){
    if(authenticationService.authenticate(email,token)) {
         boolean foodOrdered = orderService.addOrder(order,email);

         return  foodOrdered?"Food ordered":"The food you are trying to order is Not available in the menu";

    }
    else {
        return "Sign in to order Food";
       }
    }

    @GetMapping("foods")
    public List<Food> getAllFoodItems()
    {
        return foodService.getAllFoodItems();
    }


}
