package com.practice.springboard.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practice.springboard.home.service.HomeService;

@Controller
public class HomeController {
	
	private final HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService) {
	    this.homeService = homeService;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("posts", homeService.getAllPosts());
		return "home";
	}
}
