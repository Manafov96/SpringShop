package com.SpringShop.controller.admin;

import com.SpringShop.entity.web.User;
import com.SpringShop.service.api.OrderService;
import com.SpringShop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminCustomerController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/admin/customer")
	public String index(Model model) {
		model.addAttribute("customers", userService.findAll());
		return "admin/customer_list";
	}
	
	@GetMapping("/admin/customer/{id}/orders")
	public String showOrders(@PathVariable("id") Integer id, Model model) {
		User customer = userService.findOne(id);
		
		model.addAttribute("customer", customer);
		model.addAttribute("orders", orderService.findByUser(customer));
		return "admin/customer_orders";
	}
	
	@GetMapping("/admin/customer/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirect) {
		userService.delete(id);
		
		redirect.addFlashAttribute("success", "Успешно изтрит клиент!");
		return "redirect:/admin/customer";
	}
	
}
