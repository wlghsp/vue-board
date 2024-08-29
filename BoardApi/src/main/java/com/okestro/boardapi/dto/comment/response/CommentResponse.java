package com.okestro.boardapi.dto.comment.response;

import com.okestro.boardapi.model.CommentEntity;
import lombok.Getter;

@Getter
public class CommentResponse {
    private long id;
    private String content;
    private String writer;

    public CommentResponse(CommentEntity comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
    }
}