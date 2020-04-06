package com.sushi.sushihouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sushi.sushihouse.entity.Ingredient;
import com.sushi.sushihouse.service.SushiHouseService;
import com.sushi.sushihouse.utility.AddIngridientContainer;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/")
	public String getMainContent(Model model) {
		model.addAttribute("ingredients", sushiHouseService.findAllIngredient());
		return "purchase";
	}
	
	@GetMapping("/add")
	public String add(@RequestParam("id") int id, Model model) {
		model.addAttribute("ingredient", sushiHouseService.findIngredientById(id));
		AddIngridientContainer addIngridientContainer = new AddIngridientContainer();
		addIngridientContainer.setId(id);
		model.addAttribute("addIngridientContainer", addIngridientContainer);
		return "purchase-add-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("addIngridientContainer") AddIngridientContainer ingridientContainer) {
		Ingredient ingredient = sushiHouseService.findIngredientById(ingridientContainer.getId());
		ingredient.setMassNow(ingredient.getMassNow()+ingridientContainer.getAdd());
		sushiHouseService.saveIngredient(ingredient);
		return "redirect:/purchase/";
	}
	
}
