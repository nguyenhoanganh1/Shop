package com.eshop.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.eshop.bean.CartItem;
import com.eshop.dao.ProductDAO;
import com.eshop.entity.Product;

@SessionScope
@Service("cart") // attribute = scopedTarget.cart
public class ShoppingCartService {
	@Autowired
	private ProductDAO dao;
	
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
	
	public CartItem add(Integer id) {
		CartItem item = map.get(id);
		if(item != null) {
			item.setQty(item.getQty() + 1);
		}
		else {
			Product p = dao.getOne(id);
			item = new CartItem(p.getId(), p.getName(), p.getUnitPrice(), p.getDiscount(), 1);
			map.put(item.getId(), item);
		}
		return item;
	}
	
	public CartItem update(Integer id, int qty) {
		CartItem item = map.get(id);
		item.setQty(qty);
		return item;
	}
	
	public CartItem remove(Integer id) {
		return map.remove(id);
	}
	
	public void clear() {
		map.clear();
	}
	
	public Collection<CartItem> getItems(){
		return map.values();
	}
	
	public CartItem getItem(Integer id) {
		return map.get(id);
	}
	
	public int getCount() {
		int total = 0;
		for(CartItem item: map.values()) {
			total += item.getQty();
		}
		return total;
	}
	
	public double getAmount() {
		double total = 0;
		for(CartItem item: map.values()) {
			total += item.getAmount();
		}
		return total;
	}

}
