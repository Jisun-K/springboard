package com.practice.springboard.comment.controller;

import com.practice.springboard.comment.controller.dto.CommentCreateRequest;
import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public String saveComment(@PathVariable Long postId, @ModelAttribute CommentCreateRequest request) {
        commentService.saveComment(postId, request);
        return "redirect:/post/" + postId;
    }

    @ResponseBody
    @PostMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<?> deleteCommentWithPasswordCheck(@PathVariable Long postId,
                                                         @PathVariable Long commentId,
                                                         @RequestParam String password) {
        commentService.deleteCommentWithPasswordCheck(postId, commentId, password);
        return ResponseEntity.ok().build();
    }
}
