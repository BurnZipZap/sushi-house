package com.sushi.sushihouse.service;

import java.util.List;

import com.sushi.sushihouse.entity.Authorities;
import com.sushi.sushihouse.entity.Dish;
import com.sushi.sushihouse.entity.DishIngredient;
import com.sushi.sushihouse.entity.Feedback;
import com.sushi.sushihouse.entity.Geolocation;
import com.sushi.sushihouse.entity.Ingredient;
import com.sushi.sushihouse.entity.Order;
import com.sushi.sushihouse.entity.OrderDish;
import com.sushi.sushihouse.entity.Users;

public interface SushiHouseService {
	
	//Authorities
	public List<Authorities> findAllAuthorities();
	public void saveAuthorities(Authorities obj);
	public Authorities findAuthoritiesById(int id);
	public void deleteAuthoritiesById(int id);
	
	//Dish
	public List<Dish> findAllDish();
	public void saveDish(Dish obj);
	public Dish findDishById(int id);
	public void deleteDishById(int id);
	
	//DishIngredient
	public List<DishIngredient> findAllDishIngredient();
	public void saveDishIngredient(DishIngredient obj);
	public DishIngredient findDishIngredientById(int id);
	public void deleteDishIngredientById(int id);
	
	//Feedback
	public List<Feedback> findAllFeedback();
	public void saveFeedback(Feedback obj);
	public Feedback findFeedbackById(int id);
	public void deleteFeedbackById(int id);
	
	//Geolocation
	public List<Geolocation> findAllGeolocation();
	public void saveGeolocation(Geolocation obj);
	public Geolocation findGeolocationById(int id);
	public void deleteGeolocationById(int id);
	public Geolocation getGeolocationIfExist(Geolocation geolocation);
	
	//Ingredient
	public List<Ingredient> findAllIngredient();
	public void saveIngredient(Ingredient obj);
	public Ingredient findIngredientById(int id);
	public void deleteIngredientById(int id);
	
	//Order
	public List<Order> findAllOrder();
	public List<Order> findAllOrderByStatus(String status);
	public void saveOrder(Order obj);
	public Order findOrderById(int id);
	public void deleteOrderById(int id);
	public void updateStatusOrderById(int id, String status);
	
	//OrderDish
	public List<OrderDish> findAllOrderDish();
	public void saveOrderDish(OrderDish obj);
	public OrderDish findOrderDishById(int id);
	public void deleteOrderDishById(int id);
	
	//Users
	public List<Users> findAllUsers();
	public void saveUsers(Users obj);
	public Users findUsersById(int id);
	public void deleteUsersById(int id);
	public Users findUserByUsername(String name);
}
