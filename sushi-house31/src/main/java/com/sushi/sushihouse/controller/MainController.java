package com.sushi.sushihouse.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sushi.sushihouse.entity.Geolocation;
import com.sushi.sushihouse.entity.Order;
import com.sushi.sushihouse.entity.OrderDish;
import com.sushi.sushihouse.entity.Users;
import com.sushi.sushihouse.service.SushiHouseService;
import com.sushi.sushihouse.utility.StatusOrder;

@Controller
public class MainController {

	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/")
	public String getMainContent(Model model) {
		model.addAttribute("dishs", sushiHouseService.findAllDish());
		return "main";
	}
	
	@GetMapping("/addDish")
	public String addDish(@RequestParam("id") int id, 
			@CookieValue(value = "order", required=false ) String orderCookie, HttpServletResponse response) {
		addDishInOrderCookie(response, orderCookie, id);
		return "redirect:/";
	}

	@GetMapping("/showOrder")
	public String getOrderForm(@CookieValue(value = "order", required=false ) String orderCookie, Model model) {
		if(orderCookie == null || orderCookie.equals("")) {
			return "orderIsNull";
		}
		List<OrderDish> orderDishs = parseCookieToOrderDishList(orderCookie);
		model.addAttribute("orderDishs", orderDishs);
		model.addAttribute("geolocation", new Geolocation());
		model.addAttribute("order", new Order());
		return "order-form";
	}
	
	@GetMapping("/autowiredOrderForm")
	public String autowiredOrderForm(@CookieValue(value = "order", required=false ) String orderCookie, Model model) {
		if(orderCookie == null) {
			return "orderIsNull";
		}
		model.addAttribute("orderDishs", parseCookieToOrderDishList(orderCookie));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users users = sushiHouseService.findUserByUsername(authentication.getName());
		if(!authentication.getName().equals("anonymousUser")) {
			model.addAttribute("geolocation", users.getGeolocation());
		}else {
			model.addAttribute("geolocation", new Geolocation());
		}
		model.addAttribute("order", new Order());
		return "order-form";
	}
	
	@PostMapping("/saveOrder")
	public String saveOrder(@CookieValue(value = "order", required=false ) String orderCookie, 
			HttpServletResponse response, Model model, @ModelAttribute("order") Order order,
			@ModelAttribute("geolocation") Geolocation geolocationForm) {
		List<OrderDish> orderDishs = parseCookieToOrderDishList(orderCookie);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users users = sushiHouseService.findUserByUsername(authentication.getName());
		Geolocation tempGeolocation = sushiHouseService.getGeolocationIfExist(geolocationForm);
		if(tempGeolocation==null) {
			tempGeolocation = geolocationForm;
		}
		order.setUsers(users);
		order.setStatus(StatusOrder.USER_MADE_AN_ORDER.name());
		order.addListOrderDish(orderDishs);
		order.setGeolocation(tempGeolocation);
		order.setDiscount(0);
		order.setCost(calculateCost(orderDishs));
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setDtOrder(timestamp);
		sushiHouseService.saveGeolocation(tempGeolocation);
		sushiHouseService.saveOrder(order);
		model.addAttribute("order", order);
		clearOrder(response);
		return "check";
	}

	@GetMapping("/clearOrder")
	public String clearOrderList(HttpServletResponse response) {
		clearOrder(response);
		return "redirect:/";
	}
	
	@GetMapping("/addDishByOrder")
	public String addOneDishByOrder(@RequestParam("id") int id, 
			HttpServletResponse response, @CookieValue(value = "order", required=false ) String orderCookie) {
		addDishInOrderCookie(response,orderCookie,id);
		return "redirect:/showOrder";
	}
	
	@GetMapping("/removeDishByOrder")
	public String removeOneDishByOrder(@RequestParam("id") int id, 
			HttpServletResponse response, @CookieValue(value = "order", required=false ) String orderCookie) {
		removeDishInOrderCookie(response,orderCookie,id);
		return "redirect:/showOrder";
	}

	private int calculateCost(List<OrderDish> orderDishs) {
		int cost = 0;
		for (OrderDish orderDish : orderDishs) {
			cost += orderDish.getCount() * orderDish.getDish().getCost();
		}
		return cost;
	}
	
	private void clearOrder(HttpServletResponse response) {
		Cookie cookie = new Cookie("order", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	public List<OrderDish> parseCookieToOrderDishList(String orderCookie){
		List<Integer> arrayIdList= new ArrayList<>();
		for(String idString: orderCookie.split("#")) {
			arrayIdList.add(Integer.parseInt(idString));
		}
		List<Integer> oldIdList = new ArrayList<>();
		List<OrderDish> orderDishs = new ArrayList<>();
		for (int id : arrayIdList) {
			if(findCountById(oldIdList, id) > 0) {
				continue;
			}
			oldIdList.add(id);
			OrderDish tempOrderDish = new OrderDish();
			tempOrderDish.setCount(findCountById(arrayIdList, id));
			tempOrderDish.setDish(sushiHouseService.findDishById(id));
			orderDishs.add(tempOrderDish);
		}
		return orderDishs;
	}
	
	private int findCountById(List<Integer> list, int value){
		int count = 0;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) == value) {
				count++;
			}
		}
		return count;
	}
	
	private void addDishInOrderCookie(HttpServletResponse response, String order, int id) {
		if(order == null) {	
			order = "";
		}
		order+=id+"#";
		Cookie cookie = new Cookie("order", order);
		cookie.setMaxAge(60*60); //1 hour
		response.addCookie(cookie);
	}
	
	private void removeDishInOrderCookie(HttpServletResponse response, String order, int id) {
		String idString = ""+id;
		int i = order.indexOf(idString);
		String newOrder = order.substring(0,i) + order.substring(i+idString.length()+1);
		Cookie cookie = new Cookie("order", newOrder);
		cookie.setMaxAge(60*60); //1 hour
		response.addCookie(cookie);
	}
	
}