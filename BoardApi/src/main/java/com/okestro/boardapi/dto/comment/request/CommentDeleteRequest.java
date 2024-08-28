package com.okestro.boardapi.dto.comment.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentDeleteRequest {
    private String userId;
    private long commentId;
}
