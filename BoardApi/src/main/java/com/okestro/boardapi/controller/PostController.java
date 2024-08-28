package com.okestro.boardapi.controller;


import com.okestro.boardapi.dto.post.request.PostCreateRequest;
import com.okestro.boardapi.dto.post.request.PostDeleteRequest;
import com.okestro.boardapi.dto.post.request.PostUpdateRequest;
import com.okestro.boardapi.dto.post.response.PostResponse;
import com.okestro.boardapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public void savePost(@RequestBody PostCreateRequest request) {
        postService.savePost(request);
    }

    @GetMapping("/posts")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("/post")
    public void updatePost(@RequestBody PostUpdateRequest request) {
        postService.updatePost(request);
    }

    @DeleteMapping("/post")
    public void deletePosts(@RequestBody PostDeleteRequest request) {
        postService.deletePosts(request);
    }

}
