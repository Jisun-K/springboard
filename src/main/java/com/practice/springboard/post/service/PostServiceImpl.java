package com.practice.springboard.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void save(Post post) {
		postRepository.save(post);
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
	}

	@Override
	public Post updatePost(Long id, Post newPostData) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));

		post.setTitle(newPostData.getTitle());
		post.setContent(newPostData.getContent());

		return postRepository.save(post);
	}
}
