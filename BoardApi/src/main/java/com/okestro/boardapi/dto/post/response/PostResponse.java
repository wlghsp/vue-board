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
    private long id;
    private String title;
    private String content;
    private String writer;
    private List<CommentResponse> commentResponses;

    public PostResponse(long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static PostResponse of(PostEntity post) {
        return PostResponse
                .builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .commentResponses(
                        post.getCommentEntities()
                                .stream()
                                .map(CommentResponse::new)
                                .collect(Collectors.toList())
                )
                .build();
    }
}