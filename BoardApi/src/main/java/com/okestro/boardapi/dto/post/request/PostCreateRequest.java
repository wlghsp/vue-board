package com.okestro.boardapi.dto.post.request;

import lombok.Getter;

@Getter
public class PostCreateRequest {
    private String title;
    private String content;
    private String userId;
    private String password;
}
