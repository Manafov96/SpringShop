package com.SpringShop.controller.web;
import com.SpringShop.entity.web.Cart;
import com.SpringShop.service.api.CategoryService;
import com.SpringShop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

    Cart cart = new Cart();

    @GetMapping( "/login" )
	public String login() {
		return "web/login";
	}

	@GetMapping(value = {"/", "/index"})
	public String home(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", productService.findAll());
		return "web/index";
	}

	@GetMapping("/index/category/{id}/products")
	public String showProducts(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", productService.findByCategoryId(id));
		return "web/index";
	}

	@GetMapping("/about")
    public String showAbout(){
	 return "web/about";
    }

    @GetMapping("/contacts")
	public String showContacts(){
		return "web/contact";
	}

    @RequestMapping("/AddToCart/{id}")
    public String AddToCart(@PathVariable("id") Integer id, Model model){
        cart.setOneProduct(productService.findOne(id));
		model.addAttribute("listProducts", cart.getProducts());
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String ShowCart(Model model){
        model.addAttribute("listProducts", cart.getProducts());
        return "web/cart";
    }

	@RequestMapping("/delete/{id}")
	public String ClearCartItem(@PathVariable("id") Integer id, Model model){
		cart.clearCartItem(productService.findOne(id));
		return "redirect:/cart";
	}


}
