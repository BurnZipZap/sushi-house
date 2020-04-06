package com.sushi.sushihouse.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sushi.sushihouse.entity.Order;
import com.sushi.sushihouse.service.SushiHouseService;
import com.sushi.sushihouse.utility.StatusOrder;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/")
	public String getMainContent(Model model) {
		List<Order> orderByDelivery = new ArrayList<>();
		orderByDelivery.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.COOK_ORDER_IS_READY.toString()));
		orderByDelivery.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.DELIVERY_PROBLEM.toString()));
		orderByDelivery.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.DELIVERY_NOT_PROBLEM.toString()));
		model.addAttribute("orderByDelivery",orderByDelivery);
		return "delivery";
	}
	
	@GetMapping("/accept")
	public String addOrder(@RequestParam("id") int id, Model model) {
		Order order = sushiHouseService.findOrderById(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setStatus(StatusOrder.DELIVERED_ORDER.toString());
		order.setDtDelivered(timestamp);
		sushiHouseService.saveOrder(order);
		return "redirect:/delivery/";
	}
	
	@GetMapping("/problem")
	public String problemOrder(@RequestParam("id") int id, Model model) {
		sushiHouseService.updateStatusOrderById(id, StatusOrder.DELIVERY_PROBLEM.toString());
		return "redirect:/delivery/";
	}
	
}
