package com.geekster.Restaurant.management.service.API.Service;

import com.geekster.Restaurant.management.service.API.Model.Food;
import com.geekster.Restaurant.management.service.API.Model.Order;
import com.geekster.Restaurant.management.service.API.Model.User;
import com.geekster.Restaurant.management.service.API.Repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    IOrderRepo iOrderRepo;
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;
    public boolean addOrder(Order order,String email) {
        Food food = order.getFood();

        boolean foodAvail = foodService.isFoodInTheMenu(food);
        User user = userService.findFirstByUserEmail(email);
        if(foodAvail)
        {
            order.setCustomer(user);
            iOrderRepo.save(order);
            return true;
        }
        else
            return false;


    }
}
