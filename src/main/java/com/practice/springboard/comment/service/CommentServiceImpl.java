package com.practice.springboard.comment.service;

import com.practice.springboard.comment.controller.dto.CommentCreateRequest;
import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.repository.CommentRepository;
import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;
import com.practice.springboard.util.security.PasswordValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PasswordValidator passwordValidator;

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        Map<Comment, List<Comment>> groupedCommentsByParent = comments.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getParent() != null ? c.getParent() : c
                ));

        return groupedCommentsByParent.entrySet().stream().map((entry) -> {
                    Comment parent = entry.getKey();
                    List<Comment> replies = entry.getValue();

                    List<CommentResponseDto> repliesDto = replies.stream()
                            .filter(r -> r.getId() != parent.getId())
                            .map(r -> {
                                return CommentResponseDto.builder()
                                        .id(r.getId())
                                        .writer(r.getWriter())
                                        .createdAt(r.getCreatedAt())
                                        .content(r.getContent())
                                        .parentId(r.getParent().getId())
                                        .build();
                            }).toList();

                    return CommentResponseDto.builder()
                            .id(parent.getId())
                            .writer(parent.getWriter())
                            .createdAt(parent.getCreatedAt())
                            .content(parent.getContent())
                            .replies(repliesDto)
                            .build();
                }).sorted(Comparator.comparing(CommentResponseDto::getCreatedAt))
                .toList();
    }

    @Transactional
    @Override
    public void saveComment(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + postId));

        Comment newComment = Comment.createComment(request.getWriter(), request.getContent(), post);

        if (request.getParentId() != null) {
            Comment parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
            newComment.syncParent(parent);
        }

        commentRepository.save(newComment);
    }

//    @Transactional
//    @Override
//    public void delete(Long id) {
//        commentRepository.deleteById(id);
//    }

    @Transactional
    @Override
    public void deleteCommentWithPasswordCheck(Long postId, Long commentId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다."));
        if (!passwordValidator.matches(password, post.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        commentRepository.deleteById(commentId);
    }
//    @Override
//    public Comment getById(Long id) {
//        return commentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
//    }
}
