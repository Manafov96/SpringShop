package com.SpringShop.controller.web;

import com.SpringShop.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/login")
	public String login() {
		return "web/login";
	}

	@GetMapping("/index")
	public String home(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "web/index";
	}

}
