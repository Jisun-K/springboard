package com.practice.springboard.post.service;

import com.practice.springboard.post.model.Post;

public interface PostService {

	public void save(Post post);
	public void delete(Long id);
	public Post getPost(Long id);
	public Post updatePost(Long id, Post newPostData);
}
