package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Product;

public interface ProductService {
	public Product saveProduct(Product product);

	public List<Product> getAllProducts();

	public Boolean deleteProduct(int id);

	public Product getProductById(int id);

	public Product updateProduct(Product product, MultipartFile file);
}
