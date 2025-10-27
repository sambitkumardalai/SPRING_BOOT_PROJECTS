package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.service.CategoryService;
import com.ecom.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/products")
	public String products(Model m) throws JsonProcessingException {
		List<Category> categories = categoryService.getAllActiveCategory();
		List<Product> products = productService.getAllActiveProducts();

		m.addAttribute("categories", categories);
		m.addAttribute("products", products);
//		ObjectMapper mapper = new ObjectMapper(); 
//		log.info("Products Pretty JSON:\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
//		log.info("Products Pretty JSON:\n{}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categories));

		return "product";
	}
}
