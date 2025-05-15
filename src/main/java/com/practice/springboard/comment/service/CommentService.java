package com.practice.springboard.comment.service;

import com.practice.springboard.comment.controller.dto.CommentCreateRequest;
import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.model.Comment;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> getCommentsByPostId(Long postId);
    void saveComment(Long postId, CommentCreateRequest request);
    void deleteCommentWithPasswordCheck(Long postId, Long commentId, String password);
//    void delete(Long id);
//    Comment getById(Long id);
}
