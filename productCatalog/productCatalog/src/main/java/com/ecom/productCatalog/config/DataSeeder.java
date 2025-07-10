package com.ecom.productCatalog.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecom.productCatalog.model.Category;
import com.ecom.productCatalog.model.Product;
import com.ecom.productCatalog.repository.CategoryRepository;
import com.ecom.productCatalog.repository.ProductRepository;

@Component
public class DataSeeder implements CommandLineRunner {
	private final ProductRepository productRepo;
	private final CategoryRepository categoryRepo;

	public DataSeeder(ProductRepository productRepo, CategoryRepository categoryRepo) {
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		productRepo.deleteAll();
		categoryRepo.deleteAll();

		// Create Categories
		Category electronics = new Category();
		electronics.setName("Electronics");

		Category clothing = new Category();
		clothing.setName("Clothing");

		Category home = new Category();
		home.setName("Home and Kitchen");

		categoryRepo.saveAll(Arrays.asList(electronics, home, clothing));

		Product phone = new Product();
		phone.setName("SmartPhone");
		phone.setDescription("Latest model smartphone with amazing features");
		phone.setImageUrl("https://placehold.co/600x400");
		phone.setPrice(699.99);
		phone.setCategory(electronics);

		Product laptop = new Product();
		laptop.setName("Laptop");
		laptop.setDescription("High-performance laptop for work and play.");
		laptop.setImageUrl("https://placehold.co/600x400");
		laptop.setPrice(999.99);
		laptop.setCategory(electronics);

		Product jacket = new Product();
		jacket.setName("Winter Jacket");
		jacket.setDescription("Warm and cozy jacket for winter.");
		jacket.setImageUrl("https://placehold.co/600x400");
		jacket.setPrice(129.99);
		jacket.setCategory(clothing);

		Product blender = new Product();
		blender.setName("Blender");
		blender.setDescription("High-speed blender for smoothies and more.");
		blender.setImageUrl("https://placehold.co/600x400");
		blender.setPrice(89.99);
		blender.setCategory(home);

		productRepo.saveAll(Arrays.asList(phone, laptop, jacket, blender));
	}
}
