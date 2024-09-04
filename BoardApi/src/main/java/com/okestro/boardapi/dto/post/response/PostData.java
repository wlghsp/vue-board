package com.okestro.boardapi.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PostData {
    private List<PostResponse> contents;
    private PageInfo pagination;
}