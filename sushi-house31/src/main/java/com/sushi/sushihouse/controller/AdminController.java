package com.sushi.sushihouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sushi.sushihouse.entity.Order;
import com.sushi.sushihouse.service.SushiHouseService;
import com.sushi.sushihouse.utility.CountOrdersContainer;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/")
	public String getMainContent(Model model) {
		List<Order> orders = sushiHouseService.findAllOrder();
		CountOrdersContainer container = new CountOrdersContainer(orders);
		model.addAttribute("container",container);
		return "admin";
	}
	
}
