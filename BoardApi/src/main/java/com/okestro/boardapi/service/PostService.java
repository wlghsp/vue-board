package com.okestro.boardapi.service;


import com.okestro.boardapi.dto.post.request.PostCreateRequest;
import com.okestro.boardapi.dto.post.request.PostDeleteRequest;
import com.okestro.boardapi.dto.post.request.PostUpdateRequest;
import com.okestro.boardapi.dto.post.response.PostResponse;
import com.okestro.boardapi.dto.post.response.PostsResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostResponse savePost(PostCreateRequest request);

    void updatePost(PostUpdateRequest request);

    void deletePost(Long postId);

    void deletePosts(PostDeleteRequest request);

    PostResponse getPost(Long postId);

    PostsResponse getPosts(Pageable pageable);
}
