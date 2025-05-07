package com.practice.springboard.comment.controller;

import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public String saveComment(@PathVariable Long postId, @ModelAttribute Comment comment) {

//        if (parentId != null) {
//            Comment parent = commentService.getById(parentId);
//            comment.setParent(parent);
//        }

        commentService.saveComment(postId, comment);

        return "redirect:/post/" + postId;
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.delete(commentId);
        return "redirect:/post/" + postId;
    }
}
