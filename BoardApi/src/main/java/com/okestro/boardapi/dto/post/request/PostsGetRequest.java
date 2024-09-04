package com.okestro.boardapi.dto.post.request;

import lombok.Getter;

@Getter
public class PostsGetRequest {
    private Integer perPage;
    private Integer page;
}