package com.okestro.boardapi.dto.comment.request;

import lombok.Getter;

@Getter
public class CommentUpdateRequest {
    private Long commentId;
    private String content;
}
