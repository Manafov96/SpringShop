package com.SpringShop.controller.web;

import com.SpringShop.service.api.CategoryService;
import com.SpringShop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@GetMapping("/login")
	public String login() {
		return "web/login";
	}

	@GetMapping("/index")
	public String home(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", productService.findAll());
		return "web/index";
	}

}
