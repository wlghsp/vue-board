package com.okestro.boardapi.dto.comment.request;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;
    private String writer;
    private Long postId;
}
