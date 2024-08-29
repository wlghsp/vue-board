package com.okestro.boardapi.service;


import com.okestro.boardapi.dto.post.request.PostCreateRequest;
import com.okestro.boardapi.dto.post.request.PostDeleteRequest;
import com.okestro.boardapi.dto.post.request.PostUpdateRequest;
import com.okestro.boardapi.dto.post.response.PostResponse;

import java.util.List;

public interface PostService {

    void savePost(PostCreateRequest request);

    PostResponse getPost(Long postId);

    List<PostResponse> getPosts();

    void updatePost(PostUpdateRequest request);

    void deletePosts(PostDeleteRequest request);
}
