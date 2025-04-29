package com.practice.springboard.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.springboard.post.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}