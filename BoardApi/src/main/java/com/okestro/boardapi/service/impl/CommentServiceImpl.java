package com.okestro.boardapi.service.impl;

import com.okestro.boardapi.dto.comment.request.CommentCreateRequest;
import com.okestro.boardapi.dto.comment.request.CommentDeleteRequest;
import com.okestro.boardapi.dto.comment.request.CommentUpdateRequest;
import com.okestro.boardapi.dto.comment.response.CommentResponse;
import com.okestro.boardapi.model.CommentEntity;
import com.okestro.boardapi.model.PostEntity;
import com.okestro.boardapi.repo.CommentRepository;
import com.okestro.boardapi.repo.PostRepository;
import com.okestro.boardapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public void saveComment(CommentCreateRequest request) {
        PostEntity post = postRepository.findById(request.getPostId()).orElseThrow(IllegalArgumentException::new);
        post.addComment(request);
    }

    @Override
    public List<CommentResponse> getComments(Long commentId) {
        List<CommentResponse> commentResponses = new ArrayList<>();


        return commentResponses;
    }

    @Override
    public void updateComment(CommentUpdateRequest request) {

    }

    @Override
    public void deleteComment(CommentDeleteRequest request) {

    }
}

