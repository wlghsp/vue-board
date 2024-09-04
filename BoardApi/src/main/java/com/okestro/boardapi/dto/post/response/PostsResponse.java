package com.okestro.boardapi.dto.post.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostsResponse {
    private boolean result;
    private PostData data;
}
