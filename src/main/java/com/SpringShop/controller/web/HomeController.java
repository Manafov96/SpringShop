package com.SpringShop.controller.web;

import com.SpringShop.service.api.CategoryService;
import com.SpringShop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

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
}
