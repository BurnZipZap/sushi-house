package com.sushi.sushihouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sushi.sushihouse.entity.Authorities;
import com.sushi.sushihouse.entity.Geolocation;
import com.sushi.sushihouse.entity.Users;
import com.sushi.sushihouse.service.SushiHouseService;

import javassist.expr.NewArray;

@Controller
public class LoginController {
	
	@Autowired
	SushiHouseService sushiHouseService;
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "login";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
	@GetMapping("/registry")
	public String registry(Model model) {
		model.addAttribute("users", new Users());
		return "registry-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(Model model, @ModelAttribute("users") Users users) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(users.getPassword());
        Authorities tempAuthorities = new Authorities();
		tempAuthorities.setAuthority("ROLE_USER");
        users.setPassword("{bcrypt}"+hashedPassword);
        users.setEnabled((byte) 1);
		users.addAuthorities(tempAuthorities);
		sushiHouseService.saveUsers(users);
		return "login";
	}
	
	
	
}
