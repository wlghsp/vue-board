package com.okestro.boardapi.model;

import com.okestro.boardapi.dto.comment.request.CommentCreateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @Builder
    public PostEntity(String title, String content, String userId, String password) {
        if (isPostNameEmpty(title)) {
            throw new IllegalArgumentException(String.format("잘못된 title(%s)이 들어왔습니다", title));
        }
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.password = password;
    }

    private boolean isPostNameEmpty(String bookName) {
        return !StringUtils.hasText(bookName);
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void addComment(CommentCreateRequest request) {
        this.commentEntities.add(new CommentEntity(this, request.getContent(), request.getWriter()));
    }
}
