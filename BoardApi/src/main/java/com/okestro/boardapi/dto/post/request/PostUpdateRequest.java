package com.okestro.boardapi.dto.post.request;

import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private Long postId;
    private String title;
    private String content;
    private String password;
}
