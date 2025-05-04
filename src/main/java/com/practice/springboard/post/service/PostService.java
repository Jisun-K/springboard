package com.practice.springboard.post.service;

import com.practice.springboard.post.controller.dto.CreatePostRequest;
import com.practice.springboard.post.model.Post;

public interface PostService {

	public void create(CreatePostRequest request);

	public void update(Long id, CreatePostRequest request);

	public void delete(Long id);

	public Post getPost(Long id);

	public Boolean checkPassword(Long id, String password);
}
