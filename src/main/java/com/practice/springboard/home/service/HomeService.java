package com.practice.springboard.home.service;

import java.util.List;

import com.practice.springboard.home.controller.dto.PostListResponse;
import com.practice.springboard.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeService {
	
	Page<Post> getPostList(Pageable pageable);
	Page<PostListResponse> searchPosts(String keyword, Pageable pageable);
}
