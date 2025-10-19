package com.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

	private final CategoryRepository categoryRepository;
	@Autowired
	private CategoryService categoryService;

	AdminController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/loadAddProduct")
	public String loadAddProduct() {
		return "admin/add_product";
	}

	@GetMapping("/category")
	public String category(Model m) {
		m.addAttribute("categorys", categoryService.getAllCategory());
		return "admin/category";
	}

	@PostMapping("saveCategory")
	public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,
			HttpSession session) {

		String imageName = file != null ? file.getOriginalFilename() : "default.png";

		category.setImageName(imageName);
		Boolean existCategory = categoryService.existCategory(category.getName());
		if (existCategory) {
			session.setAttribute("errorMsg", "Category name already exists");
		} else {
			Category saveCategory = categoryService.saveCategory(category);
			if (ObjectUtils.isEmpty(saveCategory)) {
				session.setAttribute("errorMsg", "Not saved ! Internal server error");
			} else {
//				get file path

				try {
					File saveFile = new ClassPathResource("static/img").getFile();
					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
							+ file.getOriginalFilename());
					System.out.println(Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING));
					;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}

				session.setAttribute("succMsg", "saved successfully.");
			}
		}

		return "redirect:/admin/category";
	}

	@GetMapping("deleteCategory/{id}")
	public String deleteCategory(@PathVariable int id, HttpSession session) {
		Boolean isDeleted = categoryService.deleteCategory(id);
		if (isDeleted) {
			session.setAttribute("succMsg", "category delete success");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
		return "admin/category";
	}

	@GetMapping("loadEditCategory/{id}")
	public String loadEditCategory(@PathVariable int id, Model m) {

		Category cat = categoryService.getCategoryById(id);
		if (cat == null) {
			throw new RuntimeException("Category not found with id: " + id);
		}
		m.addAttribute("category", cat);
		return "admin/edit_category";

	}

	@PostMapping("updateCategory")
	public String updateCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,
			HttpSession session) throws IOException {

		Category oldCategory = categoryService.getCategoryById(category.getId());
		String imageName = file.isEmpty() ? oldCategory.getImageName() : file.getOriginalFilename();
		if (!ObjectUtils.isEmpty(category)) {

			oldCategory.setName(category.getName());
			oldCategory.setIsActive(category.getIsActive());
			oldCategory.setImageName(imageName);
		}
		Category updateCategory = categoryService.saveCategory(oldCategory);
		log.info("Log ================",updateCategory);
		if (!ObjectUtils.isEmpty(updateCategory)) {

			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/img").getFile();

				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
						+ file.getOriginalFilename());

				// System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}

			session.setAttribute("succMsg", "Category update success");
		} else {
			session.setAttribute("errorMsg", "something wrong on server");
		}
		return "redirect:/admin/loadEditCategory/" + category.getId();
	}

}
