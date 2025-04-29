package com.practice.springboard.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.service.PostService;

@Controller
public class PostController {
	
	private final PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post/new")
	public String createForm(Model model) {
		 model.addAttribute("post", new Post()); // 빈 Post 넘김
		return "post/edit";
	}
	
//	@GetMapping("/post/edit/{id}")
//	public String editForm(@PathVariable Long id, Model model) {
//	    Post post = postService.findById(id); // 기존 글 가져옴
//	    model.addAttribute("post", post);
//	    return "post/edit"; // 똑같이 edit.html 사용
//	}
	
	@PostMapping("/post") 
	public String savePost(@ModelAttribute Post post) {
		postService.save(post);
		return "redirect:/";
	}
}
