package com.practice.springboard.post.controller;

import com.practice.springboard.post.controller.dto.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.practice.springboard.post.service.PostService;


@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;

	@PostMapping("/post")
	public String savePost(@ModelAttribute CreatePostRequest request) {
		postService.create(request);
		return "redirect:/";
	}

	@PostMapping("/post/{id}")
	public String updatePost(@PathVariable Long id, @ModelAttribute CreatePostRequest request) {
		postService.update(id, request);
		return "redirect:/post/" + id;
	}

	@DeleteMapping("/post/{id}")
	public String deletePost(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/";
	}

	@PostMapping("/posts/{id}/check-password")
	public String checkPassword(@PathVariable Long id, @RequestParam String password, @RequestParam String action) {
		if (postService.checkPassword(id, password)) {
			return switch (action) {
				case "edit" -> "redirect:/post/" + id + "/edit";
				case "delete" -> deletePost(id);
				default -> "redirect:/post/" + id;
			};
		}
		return "redirect:/post/" + id;
	}

}
