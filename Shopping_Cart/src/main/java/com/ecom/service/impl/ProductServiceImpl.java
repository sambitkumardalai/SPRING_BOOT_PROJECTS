package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import com.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepo;

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Boolean deleteProduct(int id) {
		Product data = productRepo.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(data)) {
			productRepo.delete(data);
			return true;
		}
		return false;
	}

}
