package com.okestro.boardapi.service;

import com.okestro.boardapi.dto.comment.request.CommentCreateRequest;
import com.okestro.boardapi.dto.comment.request.CommentDeleteRequest;
import com.okestro.boardapi.dto.comment.request.CommentUpdateRequest;
import com.okestro.boardapi.dto.comment.response.CommentResponse;

import java.util.List;

public interface CommentService {

    void saveComment(CommentCreateRequest request);

    void updateComment(CommentUpdateRequest request);

    void deleteComment(CommentDeleteRequest request);

    List<CommentResponse> getComments(Long postId);
}
