package com.SpringShop.controller.api;

import com.SpringShop.entity.web.Product;
import com.SpringShop.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/api/v1/products")
	public ResponseEntity<Iterable<Product>> findAll() {
		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/api/v1/product/latest")
	public ResponseEntity<List<Product>> findLatest() {
		return new ResponseEntity<>(productService.findLatest(0, 10), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/product/search") 
	public ResponseEntity<List<Product>> search(@RequestParam("keyword") String keyword) {
		return new ResponseEntity<>(productService.search(keyword), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/product/{id}")
	public ResponseEntity<Product> show(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(productService.findOne(id), HttpStatus.OK);
	}
		
}
