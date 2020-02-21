package com.SpringShop.controller.web;

import com.SpringShop.entity.api.Order;
import com.SpringShop.entity.web.Cart;
import com.SpringShop.service.api.OrderService;
import com.SpringShop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    Cart cart = new Cart();

    @RequestMapping("/AddToCart/{id}")
    public String AddToCart(@PathVariable("id") Integer id, Model model){
        cart.setOneProduct(productService.findOne(id));
        model.addAttribute("listProducts", cart.getProducts());
        model.addAttribute("items", cart.getItems());
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String ShowCart(Model model){
        model.addAttribute("listProducts", cart.getProducts());
        model.addAttribute("items", cart.getItems());
        return "web/cart";
    }

    @RequestMapping("/delete/{id}")
    public String ClearCartItem(@PathVariable("id") Integer id, Model model){
        cart.clearCartItem(productService.findOne(id));
        return "redirect:/cart";
    }

    @GetMapping("/order")
    public String orderInformation(Model model){
        model.addAttribute("listProducts", cart.getProducts());
        model.addAttribute("order", new Order());
        model.addAttribute("items", cart.getItems());
        return "web/order";
    }

    @PostMapping("/order/save")
    public String saveOrder(@Valid Order order, BindingResult result, Model model, RedirectAttributes redirect) {
        order.setProducts(cart.getProducts());
        orderService.save(order);
        redirect.addFlashAttribute("success", "Поръчката е създадена успешно");
        cart.clearAllCartItem();
        return "web/successOrder";
    }
}
