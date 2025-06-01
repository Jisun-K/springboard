package com.practice.springboard.home.service;

import java.util.List;

import com.practice.springboard.home.controller.dto.PostListResponse;
import com.practice.springboard.post.repository.PostRepository;
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
	private final PostRepository postRepository;

	@Override
	public Page<Post> getPostList(Pageable pageable) {
		return homeRepository.getPostList(pageable);
	}

	@Override
	public Page<PostListResponse> searchPosts(String keyword, Pageable pageable) {
		Page<Post> postPage = postRepository.search(keyword, pageable);
		return postPage.map(PostListResponse::new);
	}

}
