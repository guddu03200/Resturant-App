package com.geekster.Restaurant.management.service.API.Repository;

import com.geekster.Restaurant.management.service.API.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepo extends JpaRepository<Food,Integer> {
}
