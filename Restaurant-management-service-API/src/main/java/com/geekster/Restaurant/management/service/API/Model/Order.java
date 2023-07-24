package com.geekster.Restaurant.management.service.API.Model;

import com.geekster.Restaurant.management.service.API.Model.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer orderQuantity;
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name ="fk_food_Id")
    private Food food;

    @ManyToOne
    @JoinColumn(name ="fk_user_Id")
    private User customer;

}
/*orderID
		FoodItem   ID,
		quantity,
		userID,
		status  //status will be created, dispatch , delivered*/