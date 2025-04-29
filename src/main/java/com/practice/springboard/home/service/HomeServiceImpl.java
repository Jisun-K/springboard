package com.practice.springboard.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.springboard.home.repository.HomeRepository;
import com.practice.springboard.post.model.Post;

@Service
public class HomeServiceImpl implements HomeService {
	
	private final HomeRepository homeRepository;

    @Autowired
    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }
		
	@Override
	public List<Post> getAllPosts() {
		return homeRepository.getAllPosts();
	}
}
