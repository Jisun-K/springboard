package com.practice.springboard.comment.service;

import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.model.Comment;

import java.util.List;

public interface CommentService {

    public List<CommentResponseDto> getCommentsByPostId(Long postId);
    public void saveComment(Long postId, Comment comment);
    public void delete(Long id);
    public Comment getById(Long id);
}
