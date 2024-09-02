package com.okestro.boardapi.dto.comment.request;

import lombok.Getter;

@Getter
public class CommentDeleteRequest {
    private String userId;
    private long commentId;
}
