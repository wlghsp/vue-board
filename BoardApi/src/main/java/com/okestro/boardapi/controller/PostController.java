package com.okestro.boardapi.controller;


import com.okestro.boardapi.dto.post.request.PostCreateRequest;
import com.okestro.boardapi.dto.post.request.PostDeleteRequest;
import com.okestro.boardapi.dto.post.request.PostUpdateRequest;
import com.okestro.boardapi.dto.post.response.PostResponse;
import com.okestro.boardapi.dto.post.response.PostsResponse;
import com.okestro.boardapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public PostResponse savePost(@RequestBody PostCreateRequest request) {
        return postService.savePost(request);
    }

    @PutMapping("/post")
    public void updatePost(@RequestBody PostUpdateRequest request) {
        postService.updatePost(request);
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @DeleteMapping("/posts")
    public void deletePosts(@RequestBody PostDeleteRequest request) {
        postService.deletePosts(request);
    }

    @GetMapping("/post/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/posts")
    public PostsResponse getPosts(@RequestParam Integer perPage, @RequestParam Integer page) {
        return postService.getPosts(perPage, page);
    }

}
