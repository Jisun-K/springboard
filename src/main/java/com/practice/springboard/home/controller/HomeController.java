package com.practice.springboard.home.controller;

import com.practice.springboard.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practice.springboard.home.service.HomeService;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final HomeService homeService;

	@GetMapping("/")
	public String home(@PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
					   Model model) {
		Page<Post> posts = homeService.getPostList(pageable);
		model.addAttribute("posts", posts);
		return "home";
	}
}
