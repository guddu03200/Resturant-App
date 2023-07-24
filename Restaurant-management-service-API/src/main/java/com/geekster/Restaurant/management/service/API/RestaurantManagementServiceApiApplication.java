package com.geekster.Restaurant.management.service.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantManagementServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementServiceApiApplication.class, args);
	}

}


	/*Write CRUD operation in Spring boot on Restaurant management system

	BASIC LEVEL-1   Type of User : Admin and normal-user(signin/signup) , visitor
		when admin will be logged in they will have role of ADMIN (some added privileges...see all users etc.) and Visitor user will have visitor role (view only access) and normal-user can have all the visitor user access plus some more but not the admin level access.

		User entity : in general 3 types of user can exist : normal user (those who have an account, viewer those who are not (signed in, signed up), admin user who are not necessarily signed up or signed in but have email of the type @admin.com)
		username
		password
		email : (normal user can have any email type : ex : mainakgh@gmail.com but admin emails must be something@admin.com (validation))


		FoodItem ENTITY:

		only admin can create food items
		title
		description
		price
		ORDER ENTITY : only user can give order (validation required)
		orderID
		FoodItem   ID,
		quantity,
		userID,
		status  //status will be created, dispatch , delivered

		Below task is optional :
		Intermediate LEVEL
		ADD date and time on Each ENTITY
		ADD dummyImage URL (any sort of string which is a hyperlink) to foodItem entity*/
