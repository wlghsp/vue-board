package com.okestro.boardapi.dto.post.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PostDeleteRequest {
    private List<Long> postIds;
}
