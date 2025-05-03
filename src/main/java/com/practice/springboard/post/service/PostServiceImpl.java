package com.practice.springboard.post.service;

import com.practice.springboard.post.controller.dto.CreatePostRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void create(CreatePostRequest request) {

        postRepository.save(Post.createPost(request.getTitle(), request.getContent(), request.getWriter(), passwordEncoder.encode(request.getPassword())));
    }

    @Transactional
    @Override
    public void update(Long id, CreatePostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found: " + id));

        String password = request.getPassword();
        validatePassword(password, post.getPassword());

        post.update(request.getTitle(), request.getContent(), request.getWriter(), passwordEncoder.encode(password));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
    }

    @Override
    public Post updatePost(Long id, Post newPostData) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));

        post.setTitle(newPostData.getTitle());
        post.setContent(newPostData.getContent());

        return postRepository.save(post);
    }

    private void validatePassword(String password, String target) {
        if (!passwordEncoder.matches(password, target)) {
            throw new RuntimeException("invalid password");
        }
    }
}
