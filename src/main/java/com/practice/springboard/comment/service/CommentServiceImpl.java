package com.practice.springboard.comment.service;

import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.repository.CommentRepository;
import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        Map<Long, CommentResponseDto> dtoMap = new HashMap<>();
        List<CommentResponseDto> resultComments = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto(
                    comment.getId(),
                    comment.getWriter(),
                    comment.getCreatedAt(),
                    comment.getContent(),
                    comment.getParent() != null ? comment.getParent().getId() : null,
                    new ArrayList<>()
            );
            dtoMap.put(comment.getId(), dto);
        }

        for(Comment comment : comments) {
            CommentResponseDto dto = dtoMap.get(comment.getId());
            if(comment.getParent() == null) {
                resultComments.add(dto);
            } else {
                CommentResponseDto parentDto = dtoMap.get(comment.getParent().getId());
                if(parentDto != null) {
                    parentDto.getReplies().add(dto);
                }
            }
        }
        return resultComments;
    }

    @Override
    public void saveComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + postId));

        comment.setPost(post);

        if(comment.getParent() != null && comment.getParent().getId() != null) {
            Comment parent = commentRepository.findById(comment.getParent().getId())
                    .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
            comment.setParent(parent);
        }

        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getById(Long id) {
        return  commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
    }
}
