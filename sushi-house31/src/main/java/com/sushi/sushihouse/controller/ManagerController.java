package com.sushi.sushihouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sushi.sushihouse.entity.DishIngredient;
import com.sushi.sushihouse.entity.Ingredient;
import com.sushi.sushihouse.entity.Order;
import com.sushi.sushihouse.entity.OrderDish;
import com.sushi.sushihouse.service.SushiHouseService;
import com.sushi.sushihouse.utility.StatusOrder;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/")
	public String getMainContent(Model model) {
		List<Order> orderByManager = new ArrayList<Order>();
		orderByManager.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.USER_MADE_AN_ORDER.toString()));
		orderByManager.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.COOK_PROBLEM.toString()));
		orderByManager.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.DELIVERY_PROBLEM.toString()));
		model.addAttribute("orderByManager",orderByManager);
		return "manager";
	}
	
	@GetMapping("/add")
	public String addOrder(@RequestParam("id") int id) {
		Order order = sushiHouseService.findOrderById(id);
		List<OrderDish> orderDishs = order.getOrderDishs();
		for (OrderDish orderDish : orderDishs) {
			List<DishIngredient> dishIngredients = orderDish.getDish().getDishIngredient();
			int count = orderDish.getCount();
			for (DishIngredient dishIngredient : dishIngredients) {
				Ingredient ingredient = sushiHouseService.findIngredientById(dishIngredient.getIngredient().getId());
				ingredient.setMassNow(ingredient.getMassNow()-dishIngredient.getMass() * count);
				sushiHouseService.saveIngredient(ingredient);
			}
		}
		if(order.getStatus().equals(StatusOrder.USER_MADE_AN_ORDER.name())) {
			order.setStatus(StatusOrder.CONFIRMED_MANAGER.name());
		}else if(order.getStatus().equals(StatusOrder.COOK_PROBLEM.name())){
			order.setStatus(StatusOrder.COOK_NOT_PROBLEM.name());
		}else if(order.getStatus().equals(StatusOrder.DELIVERY_PROBLEM.name())){
			order.setStatus(StatusOrder.DELIVERY_NOT_PROBLEM.name());
		}else {
			throw new RuntimeException("Incorrect status order");
		}
		sushiHouseService.saveOrder(order);
		return "redirect:/manager/";
	}
	
	@GetMapping("/remove")
	public String removeOrder(@RequestParam("id") int id, Model model) {
		Order order = sushiHouseService.findOrderById(id);
		order.setGeolocation(null);
		sushiHouseService.saveOrder(order);
		sushiHouseService.deleteOrderById(id);
		return "redirect:/manager/";
	}
	
	@GetMapping("/update")
	public String updateOrder(@RequestParam("id") int id, Model model) {
		model.addAttribute("order", sushiHouseService.findOrderById(id));
		return "manager-order-form";
	}
	
	@GetMapping("/updateFunctionAdd")
	public String addOneDishByOrder(@RequestParam("idOrderDish") int idOrderDish, @RequestParam("value") int value) {
		OrderDish orderDish = sushiHouseService.findOrderDishById(idOrderDish);
		orderDish.setCount(orderDish.getCount()+value);
		sushiHouseService.saveOrderDish(orderDish);
		return "redirect:/manager/update?id="+orderDish.getOrder().getId();
	}

	@GetMapping("/updateFunctionRemove")
	public String removeOneDishByOrder(	@RequestParam("idOrderDish") int idOrderDish, @RequestParam("value") int value) {
		OrderDish orderDish = sushiHouseService.findOrderDishById(idOrderDish);
		if(orderDish.getCount() <= value ) {
			sushiHouseService.deleteOrderDishById(idOrderDish);
		}else {
			orderDish.setCount(orderDish.getCount()-value);
			sushiHouseService.saveOrderDish(orderDish);
		}
		return "redirect:/manager/update?id="+orderDish.getOrder().getId();
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("order") Order order){
		Order tempOrder = sushiHouseService.findOrderById(order.getId());
		tempOrder.setNumber(order.getNumber());
		tempOrder.getGeolocation().setCity(order.getGeolocation().getCity());
		tempOrder.getGeolocation().setHouse(order.getGeolocation().getHouse());
		tempOrder.getGeolocation().setPorch(order.getGeolocation().getPorch());
		tempOrder.getGeolocation().setStreet(order.getGeolocation().getStreet());
		sushiHouseService.saveOrder(tempOrder);
		return "redirect:/manager/";
	}
	
	
	
}
