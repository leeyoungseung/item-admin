package com.example.itemadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class ItemAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemAdminApplication.class, args);
	}

	@RequestMapping(value="/")
	public String home() {
		
		System.out.println("test-main");
		return "index";
	}
}
