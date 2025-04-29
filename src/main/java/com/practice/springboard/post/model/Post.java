package com.practice.springboard.post.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "post") // 반드시 소문자로
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략
	private Long id; // 게시글 ID
	private String title; // 제목
	private String content; // 내용
	private String writer; // 작성자 이름
	private LocalDateTime createdAt; // 작성일시
	private LocalDateTime updatedAt; // 수정일시
	private Long userId; // 작성자 (User FK)
	
	// 기본 생성자
	public Post() {}
	
	// 생성자
    public Post(Long id, String title, String content, String writer, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }
    
}
