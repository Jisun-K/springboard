package com.practice.springboard.post.model;

import com.practice.springboard.util.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder(access = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시글 ID
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자 이름
    private String password; // 비밀번호

    public static Post createPost(String title, String content, String writer, String password) {
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .password(password)
                .build();
    }

    public void update(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }
}
