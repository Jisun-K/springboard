package com.practice.springboard.post.controller;

import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.service.PostService;

import java.util.List;

@Controller
public class PostController {

	private final PostService postService;
	private final CommentService commentService;

	@Autowired
	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	// 글 작성
	@GetMapping("/post/new")
	public String createForm(Model model) {
		model.addAttribute("post", new Post()); // 빈 Post 넘김
		return "post/edit";
	}

	// 글 수정
	@GetMapping("/post/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		return "post/edit"; // 똑같이 edit.html 사용
	}

	// 글 상세 조회
	@GetMapping("/post/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		Post post = postService.getPost(id);
		List<Comment> comments = commentService.getCommentsByPostId(id);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "post/detail";
	}

	// 새 글 저장
	@PostMapping("/post")
	public String savePost(@ModelAttribute Post post) {
		postService.save(post);
		return "redirect:/";
	}

	// 글 수정
	@PostMapping("/post/{id}/update")
	public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
		postService.save(post);
		return "redirect:/post/" + id;
	}

	// 글 삭제
	@PostMapping("/post/{id}/delete")
	public String deletePost(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/";
	}
}
