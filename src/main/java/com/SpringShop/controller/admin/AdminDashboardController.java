package com.SpringShop.controller.admin;

import com.SpringShop.entity.web.Order;
import com.SpringShop.service.api.CategoryService;
import com.SpringShop.service.api.OrderService;
import com.SpringShop.service.api.ProductService;
import com.SpringShop.service.api.UserService;
import com.SpringShop.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping({"/admin", "/admin/dashboard"})
	public String index(Model model) {
		// Stats
		long totalUsers = userService.countAll();
		long totalCategories = categoryService.countAll();
		long totalProducts = productService.countAll();
		long totalOrders = orderService.countAll();
		
		// Fetch top 5 newest orders;
		Page<Order> newestOrders = orderService.findLatest(0, Const.TOTAL_NEWEST_ORDERS);
		
		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalCategories", totalCategories);
		model.addAttribute("totalProducts", totalProducts);
		model.addAttribute("totalOrders", totalOrders);
		model.addAttribute("newestOrders", newestOrders);
		return "admin/dashboard";
	}
	
}
