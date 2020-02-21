package com.SpringShop.entity.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Item> items;

	private List<Product> products;

	private Product product;

	public Cart() {
        items = new ArrayList<>();
        products = new ArrayList<>();
    }
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public void setProducts(ArrayList<Product> products){
	    products.add(product);
    }

    public void setOneProduct(Product product){
		products.add(product);
	}

    public Product getOneProduct(Product product){
	    return product;
    }

	public List<Item> getItems() {
		return items;
	}

	public List<Product> getProducts(){
	    return products;
    }

	public int getCount() {
		return items.size();
	}

	public void clearCartItem(Product product){
		products.remove(product);
	}

	public void clearCartOneItem(Product cartItem){
		//cartItems.remove();
	}

	public void clearAllCartItem(){
		products.clear();
	}
	
	public int getTotal() {
		int total = 0;
		for (Item e : items) {
			total += e.getSubTotal();
		}
		return total;
	}
	
}