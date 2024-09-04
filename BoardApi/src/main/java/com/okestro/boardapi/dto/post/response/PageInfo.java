package com.okestro.boardapi.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageInfo {
    private Integer page;
    private Long totalCount;
}