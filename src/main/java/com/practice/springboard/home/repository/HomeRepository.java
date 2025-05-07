package com.practice.springboard.home.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;

@RequiredArgsConstructor
@Repository
public class HomeRepository {

    private final PostRepository postRepository;

    public Page<Post> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
	
}
