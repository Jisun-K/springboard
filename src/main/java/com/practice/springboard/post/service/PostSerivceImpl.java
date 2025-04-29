package com.practice.springboard.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;

@Service
public class PostSerivceImpl implements PostService {

	private final PostRepository postRepository;
	
	@Autowired
	public PostSerivceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void save(Post post) {
		postRepository.save(post);
	}
}
