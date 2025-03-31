package com.example.spring_sec_demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Helloworld {
	@GetMapping("hello")  
	public String hello() {
		return "Hi";
	}
}
