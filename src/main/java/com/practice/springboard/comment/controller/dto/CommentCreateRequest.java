package com.practice.springboard.comment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentCreateRequest {
    private String writer;
    private String content;
    private Long parentId;

}
