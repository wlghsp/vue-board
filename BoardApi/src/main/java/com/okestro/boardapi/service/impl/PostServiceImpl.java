package com.okestro.boardapi.service.impl;


import com.okestro.boardapi.dto.post.request.PostCreateRequest;
import com.okestro.boardapi.dto.post.request.PostDeleteRequest;
import com.okestro.boardapi.dto.post.request.PostUpdateRequest;
import com.okestro.boardapi.dto.post.response.PostResponse;
import com.okestro.boardapi.dto.post.response.PostsResponse;
import com.okestro.boardapi.model.PostEntity;
import com.okestro.boardapi.repo.PostRepository;
import com.okestro.boardapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostResponse savePost(PostCreateRequest request) {
        PostEntity saved = postRepository.save(new PostEntity(request.getTitle(), request.getContent(), request.getUserId(), request.getPassword()));
        return PostResponse.of(saved);
    }

    @Override
    public void updatePost(PostUpdateRequest request) {
        PostEntity post = postRepository.findById(request.getPostId()).orElseThrow(IllegalArgumentException::new);

        if (passwordNotMatched(request, post)) {
            throw new IllegalArgumentException(String.format("잘못된 password(%s)가 들어왔습니다", request.getPassword()));
        }

        post.updatePost(request.getTitle(), request.getContent());
    }

    @Override
    public void deletePost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);

        postRepository.delete(post);
    }

    @Override
    public void deletePosts(PostDeleteRequest request) {
        List<Long> postIds = request.getPostIds();

        if (isPostIdsEmpty(postIds)) {
            for (Long postId : postIds) {
                deletePost(postId);
            }
        }
    }

    @Override
    public PostResponse getPost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("해당 postId(%s)로 게시글을 찾을 수 없습니다.", postId)));

        return PostResponse.of(post);
    }

    @Transactional(readOnly = true)
    @Override
    public PostsResponse getPosts(Pageable pageable) {
        Page<PostEntity> postsPage = postRepository.findAllByOrderByIdDesc(pageable);
        long totalElements = postsPage.getTotalElements();

        List<PostEntity> postEntities = postsPage.getContent();
        List<PostResponse> postResponses = postEntities.stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle(), post.getUserId()))
                .collect(Collectors.toList());

        return PostsResponse.builder()
                .rows(totalElements)
                .postResponses(postResponses)
                .build();
    }

    private boolean passwordNotMatched(PostUpdateRequest request, PostEntity post) {
        return !post.getPassword().equals(request.getPassword());
    }

    private boolean isPostIdsEmpty(List<Long> postIds) {
        return !postIds.isEmpty();
    }

}
