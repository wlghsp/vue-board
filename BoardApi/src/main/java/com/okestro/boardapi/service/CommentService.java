package com.okestro.boardapi.service;

import com.okestro.boardapi.dto.comment.request.CommentCreateRequest;
import com.okestro.boardapi.dto.comment.request.CommentDeleteRequest;
import com.okestro.boardapi.dto.comment.request.CommentUpdateRequest;

public interface CommentService {

    void saveComment(CommentCreateRequest request);

    void updateComment(CommentUpdateRequest request);

    void deleteComment(CommentDeleteRequest request);
}
