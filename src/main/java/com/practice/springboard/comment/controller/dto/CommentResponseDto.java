package com.practice.springboard.comment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private String writer;
    private LocalDateTime createdAt;
    private String content;
    private Long parentId;
    private List<CommentResponseDto> replies = new ArrayList<>();
}
