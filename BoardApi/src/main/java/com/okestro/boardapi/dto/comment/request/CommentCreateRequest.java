package com.okestro.boardapi.dto.comment.request;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;
    private String writer;
    private String password;
    private Long postId;
}
