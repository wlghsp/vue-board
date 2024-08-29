package com.okestro.boardapi.service.impl;

import com.okestro.boardapi.dto.comment.request.CommentCreateRequest;
import com.okestro.boardapi.dto.comment.request.CommentDeleteRequest;
import com.okestro.boardapi.dto.comment.request.CommentUpdateRequest;
import com.okestro.boardapi.model.CommentEntity;
import com.okestro.boardapi.model.PostEntity;
import com.okestro.boardapi.repo.CommentRepository;
import com.okestro.boardapi.repo.PostRepository;
import com.okestro.boardapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public void saveComment(CommentCreateRequest request) {
        PostEntity post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("해당 postId(%s)로 게시글을 찾을 수 없습니다.", request.getPostId())));

        post.addComment(request);
    }

    @Override
    public void updateComment(CommentUpdateRequest request) {
        CommentEntity comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("해당 commentId(%s)로 댓글을 찾을 수 없습니다.", request.getCommentId())));

        comment.updateContent(request);
    }

    @Override
    public void deleteComment(CommentDeleteRequest request) {
        CommentEntity comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("해당 commentId(%s)로 댓글을 찾을 수 없습니다.", request.getCommentId())));

        commentRepository.delete(comment);
    }
}

