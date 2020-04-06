package com.sushi.sushihouse.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushi.sushihouse.dao.AuthoritiesDAO;
import com.sushi.sushihouse.dao.DishDAO;
import com.sushi.sushihouse.dao.DishIngredientDAO;
import com.sushi.sushihouse.dao.FeedbackDAO;
import com.sushi.sushihouse.dao.GeolocationDAO;
import com.sushi.sushihouse.dao.IngredientDAO;
import com.sushi.sushihouse.dao.OrderDAO;
import com.sushi.sushihouse.dao.OrderDishDAO;
import com.sushi.sushihouse.dao.UsersDAO;
import com.sushi.sushihouse.entity.Authorities;
import com.sushi.sushihouse.entity.Dish;
import com.sushi.sushihouse.entity.DishIngredient;
import com.sushi.sushihouse.entity.Feedback;
import com.sushi.sushihouse.entity.Geolocation;
import com.sushi.sushihouse.entity.Ingredient;
import com.sushi.sushihouse.entity.Order;
import com.sushi.sushihouse.entity.OrderDish;
import com.sushi.sushihouse.entity.Users;

@Service
public class SushiHouseServiceImpl implements SushiHouseService{
	
	AuthoritiesDAO authoritiesDAO;
	DishDAO dishDAO;
	DishIngredientDAO dishIngredientDAO;
	FeedbackDAO feedbackDAO;
	GeolocationDAO geolocationDAO;
	IngredientDAO ingredientDAO;
	OrderDAO orderDAO;
	OrderDishDAO orderDishDAO;
	UsersDAO usersDAO;

	
	@Autowired
	public SushiHouseServiceImpl(AuthoritiesDAO authoritiesDAO, DishDAO dishDAO, DishIngredientDAO dishIngredientDAO,
			FeedbackDAO feedbackDAO, GeolocationDAO geolocationDAO, IngredientDAO ingredientDAO, OrderDAO orderDAO,
			OrderDishDAO orderDishDAO, UsersDAO usersDAO) {
		this.authoritiesDAO = authoritiesDAO;
		this.dishDAO = dishDAO;
		this.dishIngredientDAO = dishIngredientDAO;
		this.feedbackDAO = feedbackDAO;
		this.geolocationDAO = geolocationDAO;
		this.ingredientDAO = ingredientDAO;
		this.orderDAO = orderDAO;
		this.orderDishDAO = orderDishDAO;
		this.usersDAO = usersDAO;
	}

	//Authorities
	@Transactional
	@Override
	public List<Authorities> findAllAuthorities() {
		return authoritiesDAO.findAll();
	}

	@Transactional
	@Override
	public void saveAuthorities(Authorities obj) {
		authoritiesDAO.save(obj);
	}

	@Transactional
	@Override
	public Authorities findAuthoritiesById(int id) {
		return authoritiesDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteAuthoritiesById(int id) {
		authoritiesDAO.deleteById(id);
	}

	//Dish
	@Transactional
	@Override
	public List<Dish> findAllDish() {
		return dishDAO.findAll();
	}

	@Transactional
	@Override
	public void saveDish(Dish obj) {
		dishDAO.save(obj);
	}

	@Transactional
	@Override
	public Dish findDishById(int id) {
		return dishDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteDishById(int id) {
		dishDAO.deleteById(id);
	}

	//DishIngredient
	@Transactional
	@Override
	public List<DishIngredient> findAllDishIngredient() {
		return dishIngredientDAO.findAll();
	}

	@Transactional
	@Override
	public void saveDishIngredient(DishIngredient obj) {
		dishIngredientDAO.save(obj);
	}

	@Transactional
	@Override
	public DishIngredient findDishIngredientById(int id) {
		return dishIngredientDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteDishIngredientById(int id) {
		dishIngredientDAO.deleteById(id);
	}

	@Transactional
	@Override
	public List<Feedback> findAllFeedback() {
		return feedbackDAO.findAll();
	}

	@Transactional
	@Override
	public void saveFeedback(Feedback obj) {
		feedbackDAO.save(obj);
	}

	@Transactional
	@Override
	public Feedback findFeedbackById(int id) {
		return feedbackDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteFeedbackById(int id) {
		feedbackDAO.deleteById(id);
	}

	@Transactional
	@Override
	public List<Geolocation> findAllGeolocation() {
		return geolocationDAO.findAll();
	}

	@Transactional
	@Override
	public void saveGeolocation(Geolocation obj) {
		geolocationDAO.save(obj);
	}

	@Transactional
	@Override
	public Geolocation findGeolocationById(int id) {
		return geolocationDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteGeolocationById(int id) {
		geolocationDAO.deleteById(id);
	}

	@Transactional
	@Override
	public List<Ingredient> findAllIngredient() {
		return ingredientDAO.findAll();
	}

	@Transactional
	@Override
	public void saveIngredient(Ingredient obj) {
		ingredientDAO.save(obj);
	}

	@Transactional
	@Override
	public Ingredient findIngredientById(int id) {
		return ingredientDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteIngredientById(int id) {
		ingredientDAO.deleteById(id);
	}

	@Transactional
	@Override
	public List<Order> findAllOrder() {
		return orderDAO.findAll();
	}

	@Transactional
	@Override
	public void saveOrder(Order obj) {
		orderDAO.save(obj);
	}

	@Transactional
	@Override
	public Order findOrderById(int id) {
		return orderDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteOrderById(int id) {
		orderDAO.deleteById(id);
	}

	@Transactional
	@Override
	public List<OrderDish> findAllOrderDish() {
		return orderDishDAO.findAll();
	}

	@Transactional
	@Override
	public void saveOrderDish(OrderDish obj) {
		orderDishDAO.save(obj);
	}

	@Transactional
	@Override
	public OrderDish findOrderDishById(int id) {
		return orderDishDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteOrderDishById(int id) {
		orderDishDAO.deleteById(id);
	}


	@Transactional
	@Override
	public List<Users> findAllUsers() {
		return usersDAO.findAll();
	}

	@Transactional
	@Override
	public void saveUsers(Users obj) {
		usersDAO.save(obj);
	}

	@Transactional
	@Override
	public Users findUsersById(int id) {
		return usersDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteUsersById(int id) {
		usersDAO.deleteById(id);
	}

	@Transactional
	@Override
	public Users findUserByUsername(String name) {
		return usersDAO.findUserByUsername(name);
	}

	@Transactional
	@Override
	public Geolocation getGeolocationIfExist(Geolocation geolocation) {
		return geolocationDAO.getGeolocationIfExist(geolocation);
	}

	@Transactional
	@Override
	public List<Order> findAllOrderByStatus(String status) {
		return orderDAO.findAllOrderByStatus(status);
	}

	@Transactional
	@Override
	public void updateStatusOrderById(int id, String status) {
		orderDAO.updateStatus(id, status);
	}	
}
