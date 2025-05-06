package com.practice.springboard.post.controller;

import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.service.CommentService;
import com.practice.springboard.post.controller.dto.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

	private final PostService postService;
	private final CommentService commentService;

	// 글 작성 이동
	@GetMapping("/post")
	public String createForm(Model model) {
		return "post/create";
	}

	// 글 수정 이동
	@GetMapping("/post/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		return "post/edit";
	}

	// 글 상세 조회 이동
	@GetMapping("/post/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		Post post = postService.getPost(id);
		List<CommentResponseDto> comments = commentService.getCommentsByPostId(id);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "post/detail";
	}

	// 새 글 저장
	@PostMapping("/post")
	public String savePost(@ModelAttribute CreatePostRequest request) {
		postService.create(request);
		return "redirect:/";
	}

	// 글 수정 저장
	@PostMapping("/post/{id}")
	public String updatePost(@PathVariable Long id, @ModelAttribute CreatePostRequest request) {
		postService.update(id, request);
		return "redirect:/post/" + id;
	}

	// 글 삭제
	@DeleteMapping("/post/{id}")
	public String deletePost(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/";
	}

	// 비밀번호 확인
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
