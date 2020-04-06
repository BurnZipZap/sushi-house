package com.sushi.sushihouse.controller;

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
@RequestMapping("/cook")
public class CookController {
	
	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/")
	public String getMainContent(Model model) {
		List<Order> orderByCook = new ArrayList<>();
		orderByCook.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.CONFIRMED_MANAGER.name()));
		orderByCook.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.COOK_NOT_PROBLEM.name()));
		orderByCook.addAll(sushiHouseService.findAllOrderByStatus(StatusOrder.COOK_PROBLEM.name()));
		model.addAttribute("orderByCook",orderByCook);
		return "cook";
	}
	
	@GetMapping("/accept")
	public String addOrder(@RequestParam("id") int id, Model model) {
		sushiHouseService.updateStatusOrderById(id, StatusOrder.COOK_ORDER_IS_READY.toString());
		return "redirect:/cook/";
	}
	
	@GetMapping("/problem")
	public String problemOrder(@RequestParam("id") int id, Model model) {
		sushiHouseService.updateStatusOrderById(id, StatusOrder.COOK_PROBLEM.toString());
		return "redirect:/cook/";
	}
	
}
