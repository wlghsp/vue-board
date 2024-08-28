package com.okestro.boardapi.dto.comment.response;

import com.okestro.boardapi.model.PostEntity;
import lombok.Getter;

@Getter
public class CommentResponse {
    private long id;
    private String content;
    private String writer;

    public CommentResponse(PostEntity post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.writer = post.getWriter();
    }
}