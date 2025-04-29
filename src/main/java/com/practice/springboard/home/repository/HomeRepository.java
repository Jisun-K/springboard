package com.practice.springboard.home.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;

@Repository
public class HomeRepository {

    private final PostRepository postRepository;
    
    @Autowired
    public HomeRepository(PostRepository postRepository) {
    	this.postRepository = postRepository;
    }
    
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	};
	
}
