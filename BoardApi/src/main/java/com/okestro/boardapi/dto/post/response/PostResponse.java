package com.okestro.boardapi.dto.post.response;

import com.okestro.boardapi.dto.comment.response.CommentResponse;
import com.okestro.boardapi.model.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class PostResponse {
    private long postId;
    private String title;
    private String content;
    private String writer;
    private List<CommentResponse> commentResponses;

    public PostResponse(long postId, String title, String writer) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
    }

    public static PostResponse of(PostEntity post) {
        return PostResponse
                .builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .build();
    }
}