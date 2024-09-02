package com.okestro.boardapi.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class PostsResponse {
    private long rows;
    private List<PostResponse> postResponses;
}