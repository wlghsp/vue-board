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

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Builder
    public CommentEntity(PostEntity post, String content, String writer, String password) {
        this.post = post;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    public void updateContent(CommentUpdateRequest request) {
        if (passwordNotEqual(request)) {
            throw new IllegalArgumentException(String.format("잘못된 password(%s)가 들어왔습니다.", request.getPassword()));
        }

        this.content = request.getContent();
    }

    private boolean passwordNotEqual(CommentUpdateRequest request) {
        return !this.password.equals(request.getPassword());
    }
}
