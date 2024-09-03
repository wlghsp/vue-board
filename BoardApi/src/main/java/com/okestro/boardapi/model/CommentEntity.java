package com.okestro.boardapi.model;

import com.okestro.boardapi.dto.comment.request.CommentUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comment")
@Entity
public class CommentEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Builder
    public CommentEntity(PostEntity post, String content, String writer) {
        this.post = post;
        this.content = content;
        this.writer = writer;
    }

    public void updateContent(CommentUpdateRequest request) {
        this.content = request.getContent();
    }

}
