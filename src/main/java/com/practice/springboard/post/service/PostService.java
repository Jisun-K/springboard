package com.practice.springboard.post.service;

import com.practice.springboard.post.controller.dto.CreatePostRequest;
import com.practice.springboard.post.model.Post;

public interface PostService {

	void create(CreatePostRequest request);

	void update(Long id, CreatePostRequest request);

	void delete(Long id);

	Post getPost(Long id);

	Boolean checkPassword(Long id, String password);
}
