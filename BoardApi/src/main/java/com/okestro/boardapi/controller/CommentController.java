package com.okestro.boardapi.controller;

import com.okestro.boardapi.dto.comment.request.CommentCreateRequest;
import com.okestro.boardapi.dto.comment.request.CommentDeleteRequest;
import com.okestro.boardapi.dto.comment.request.CommentUpdateRequest;
import com.okestro.boardapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public void saveComment(@RequestBody CommentCreateRequest request) {
        commentService.saveComment(request);
    }

    @PutMapping("/comment")
    public void updateComment(@RequestBody CommentUpdateRequest request) {
        commentService.updateComment(request);
    }

    @DeleteMapping("/comment")
    public void deleteComment(@RequestBody CommentDeleteRequest request) {
        commentService.deleteComment(request);
    }
}
