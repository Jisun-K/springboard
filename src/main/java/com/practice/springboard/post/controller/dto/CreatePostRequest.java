package com.practice.springboard.post.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CreatePostRequest {
    private String title;
    private String content;
    private String writer;
    private String password;
    private String newPassword;
}
