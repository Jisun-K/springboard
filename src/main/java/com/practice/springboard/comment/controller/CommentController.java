package com.practice.springboard.comment.controller;

import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/comment")
    public String saveComment(@PathVariable Long postId, @ModelAttribute Comment comment,
                              @RequestParam(value = "parentId", required = false) Long parentId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
        comment.setWriter("sample_temp"); // writer 자동 설정

        if (parentId != null) {
            Comment parent = commentService.getById(parentId);  // 이 메서드가 없다면 추가해야 해!
            comment.setParent(parent);
        }

        commentService.saveComment(postId, comment);

        return "redirect:/post/" + postId;
    }

    // 댓글 삭제
    @PostMapping("/post/{postId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.delete(commentId);
        return "redirect:/post/" + postId;
    }
}
