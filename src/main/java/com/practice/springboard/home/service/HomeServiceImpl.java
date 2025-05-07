package com.practice.springboard.home.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.springboard.home.repository.HomeRepository;
import com.practice.springboard.post.model.Post;

@RequiredArgsConstructor
@Service
public class HomeServiceImpl implements HomeService {
	
	private final HomeRepository homeRepository;

	@Override
	public Page<Post> getPostList(Pageable pageable) {
		return homeRepository.getPostList(pageable);
	}
}
