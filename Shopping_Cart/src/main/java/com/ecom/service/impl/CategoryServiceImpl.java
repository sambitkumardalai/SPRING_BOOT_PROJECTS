package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecom.ShoppingCartApplication;
import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final ShoppingCartApplication shoppingCartApplication;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CategoryRepository categoryRepo;

	CategoryServiceImpl(ShoppingCartApplication shoppingCartApplication) {
		this.shoppingCartApplication = shoppingCartApplication;
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	@Override
	public boolean existCategory(String name) {
		return categoryRepo.existsByName(name);
	}

	@Override
	public Boolean deleteCategory(int id) {
		/*
		 * String sql = "DELETE FROM category WHERE id = ?"; return
		 * jdbcTemplate.update(sql,id) > 0;
		 */

		Category cat = categoryRepo.findById(id).orElse(null);
		if (!ObjectUtils.isEmpty(cat)) {
			categoryRepo.delete(cat);
			return true;
		}
		return false;
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepo.findById(id).orElse(null);
	}

}
