package com.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.UserDtls;
import com.ecom.service.CategoryService;
import com.ecom.service.ProductService;
import com.ecom.service.UserService;
import com.ecom.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	private CommonUtil commonUtil;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute
	public void getUserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls userDtls = userService.getUserByEmail(email);
			m.addAttribute("user", userDtls);
		}

		List<Category> allActiveCategory = categoryService.getAllActiveCategory();
		m.addAttribute("categorys", allActiveCategory);
	}

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

	@GetMapping("/product/{id}")
	public String product(@PathVariable int id, Model m) {
		Product productById = productService.getProductById(id);
		m.addAttribute("product", productById);
		return "view_product";
	}

	@PostMapping("saveUser")
	public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session)
			throws IOException {
		String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();

		user.setProfileImage(imageName);
		UserDtls saveUser = userService.saveUser(user);
		if (!ObjectUtils.isEmpty(saveUser)) {
			if (!file.isEmpty()) {
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
						+ file.getOriginalFilename());

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			}

			session.setAttribute("succMsg", "Register successfully");
		} else {
			session.setAttribute("errorMsg", "Something went wrong");
		}

		return "redirect:/register";
	}

	@GetMapping("/forgot-password")
	public String showForgotPassword() {
		return "forgot_password.html";
	}

	@PostMapping("forgot-password")
	public String processForgotPassword(@RequestParam String email, HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {
		UserDtls userByEmail = userService.getUserByEmail(email);

		if (userByEmail == null) {
			session.setAttribute("errorMsg", "Invalid email");
		} else {
			String resetToken = UUID.randomUUID().toString();
			userService.updateUserResetToken(email, resetToken);

			String url = CommonUtil.generateUrl(request) + "/reset-password?token=" + resetToken;

			Boolean sendMail = commonUtil.sendMail(url, email);

			if (sendMail) {
				session.setAttribute("succMsg", "Please check your email..Password Reset link sent");
			} else {
				session.setAttribute("errorMsg", "Somethong wrong on server ! Email not send");
			}
		}
		return "redirect:/forgot-password";
	}

	@GetMapping("/reset-password")
	public String showResetPassword(@RequestParam String token, HttpSession session, Model m) {

		UserDtls userByToken = userService.getUserByToken(token);

		if (userByToken == null) {
			m.addAttribute("msg", "Your link is invalid or expired !!");
			return "message";
		}
		m.addAttribute("token", token);
		return "reset_password";
	}

	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam String token, @RequestParam String password, HttpSession session,
			Model m) {

		UserDtls userByToken = userService.getUserByToken(token);
		if (userByToken == null) {
			m.addAttribute("errorMsg", "Your link is invalid or expired !!");
			return "message";
		} else {
			userByToken.setPassword(passwordEncoder.encode(password));
			userByToken.setResetToken(null);
			userService.updateUser(userByToken);
			// session.setAttribute("succMsg", "Password change successfully");
			m.addAttribute("msg", "Password change successfully");

			return "message";
		}

	}
}
