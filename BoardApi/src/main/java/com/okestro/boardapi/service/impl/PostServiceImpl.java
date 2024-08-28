package com.okestro.boardapi.service.impl;


import com.okestro.boardapi.model.PostEntity;
import com.okestro.boardapi.dto.post.request.PostCreateRequest;
import com.okestro.boardapi.dto.post.request.PostDeleteRequest;
import com.okestro.boardapi.dto.post.request.PostUpdateRequest;
import com.okestro.boardapi.dto.post.response.PostResponse;
import com.okestro.boardapi.repo.PostRepository;
import com.okestro.boardapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional
    @Override
    public void savePost(PostCreateRequest request) {
        postRepository.save(new PostEntity(request.getTitle(), request.getContent(), request.getWriter(), request.getPassword()));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> getPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updatePost(PostUpdateRequest request) {
        PostEntity post = postRepository.findById(request.getPostId()).orElseThrow(IllegalArgumentException::new);

        if (passwordNotMatched(request, post)) {
            throw new IllegalArgumentException(String.format("잘못된 password(%s)가 들어왔습니다", request.getPassword()));
        }

        post.updatePost(request.getTitle(), request.getContent());
    }

    private boolean passwordNotMatched(PostUpdateRequest request, PostEntity post) {
        return !post.getPassword().equals(request.getPassword());
    }

    @Transactional
    @Override
    public void deletePosts(PostDeleteRequest request) {
        List<Long> postIds = request.getPostIds();

        if (isPostIdsEmpty(postIds)) {
            for (Long postId : postIds) {
                deletePost(postId);
            }
        }
    }

    private void deletePost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);

        postRepository.delete(post);
    }

    private boolean isPostIdsEmpty(List<Long> postIds) {
        return !postIds.isEmpty();
    }

}
