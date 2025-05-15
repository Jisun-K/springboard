package com.practice.springboard.post.controller;

import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.service.CommentService;
import com.practice.springboard.post.controller.dto.CreatePostRequest;
import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostViewController {

	private final PostService postService;
	private final CommentService commentService;

	@GetMapping("/post")
	public String createForm() {
		return "post/create";
	}

	@GetMapping("/post/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		return "post/edit";
	}

	@GetMapping("/post/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		Post post = postService.getPost(id);
		List<CommentResponseDto> comments = commentService.getCommentsByPostId(id);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "post/detail";
	}
}
