package com.ecom.service;

import com.ecom.model.Cart;

public interface CartService {
	
	public Cart saveCart(Integer productId,Integer userId);
}
