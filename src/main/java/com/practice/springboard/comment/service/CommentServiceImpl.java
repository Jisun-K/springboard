package com.practice.springboard.comment.service;

import com.practice.springboard.comment.controller.dto.CommentCreateRequest;
import com.practice.springboard.comment.controller.dto.CommentResponseDto;
import com.practice.springboard.comment.model.Comment;
import com.practice.springboard.comment.repository.CommentRepository;
import com.practice.springboard.post.model.Post;
import com.practice.springboard.post.repository.PostRepository;
import jakarta.transaction.Transactional;
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
            Long commentId = comment.getId();
            Long parentId = comment.getParent() != null ? comment.getParent().getId() : null;

            CommentResponseDto dto = new CommentResponseDto(
                    commentId,
                    comment.getWriter(),
                    comment.getCreatedAt(),
                    comment.getContent(),
                    parentId,
                    new ArrayList<>()
            );

            dtoMap.put(commentId, dto);

            if (parentId == null) {
                resultComments.add(dto);
            } else {
                dtoMap.computeIfPresent(parentId, (id, parentDto) -> {
                    parentDto.getReplies().add(dto);
                    return parentDto;
                });
            }
        }

        return resultComments;
    }

    @Transactional
    @Override
    public void saveComment(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + postId));

        Comment newComment = Comment.createComment(request.getWriter(), request.getContent(), post);

        if (request.getParentId() != null) {
            Comment parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
            newComment.syncParent(parent);
        }

        commentRepository.save(newComment);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
    }
}
