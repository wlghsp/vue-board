package com.okestro.boardapi.dto.post.response;

import com.okestro.boardapi.model.PostEntity;
import lombok.Getter;

@Getter
public class PostResponse {
    private long id;
    private String title;
    private String content;
    private String writer;

    public PostResponse(PostEntity post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter();
    }
}