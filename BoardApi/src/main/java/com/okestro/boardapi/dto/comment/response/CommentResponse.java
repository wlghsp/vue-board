package com.okestro.boardapi.dto.comment.response;

import com.okestro.boardapi.model.CommentEntity;
import lombok.Getter;

@Getter
public class CommentResponse {
    private long commentId;
    private String content;
    private String writer;
    private Long postId;

    public CommentResponse(CommentEntity comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.postId = comment.getPost().getId();
    }
}