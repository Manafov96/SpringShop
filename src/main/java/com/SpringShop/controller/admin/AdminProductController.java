package com.SpringShop.controller.admin;

import com.SpringShop.entity.web.Category;
import com.SpringShop.entity.web.Product;
import com.SpringShop.service.api.CategoryService;
import com.SpringShop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/admin/product")
	public String index(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin/product_list";
	}

	@GetMapping("/admin/product/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.findAll());
		return "admin/product_form";
	}

	@GetMapping("/admin/product/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.findOneWithCategory(id));
		model.addAttribute("categories", categoryService.findAll());
		return "admin/product_form";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Category category = categoryService.findOne(Integer.parseInt(text));
                setValue(category);
            }
        });
    }

	@PostMapping("/admin/product/save")
	public String save(@Valid Product product, BindingResult result, Model model, RedirectAttributes redirect) {
		model.addAttribute("categories", categoryService.findAll());
		
		if (result.hasErrors()) {
			return "admin/product_form";
		}

		productService.save(product);

		redirect.addFlashAttribute("success", "Продукта е записан успешно");
		return "redirect:/admin/product";
	}

	@GetMapping("/admin/product/{id}/delete")
	public String delete(@PathVariable Integer id, RedirectAttributes redirect) {
		productService.delete(id);

		redirect.addFlashAttribute("success", "Успешно изтрихте продукта");
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/{id}/upload")
	public String getUpload(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", productService.findOne(id));
		return "admin/product_upload";
	}
	
	@PostMapping("/admin/product/{id}/upload")
	public String postUpload(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile imageFile, 
			RedirectAttributes redirect) {
		Product product = productService.findOne(id);
		productService.upload(product, imageFile);
		
		redirect.addFlashAttribute("success", "Качването на снимката е успешно!");
		return "redirect:/admin/product";
	}

}
