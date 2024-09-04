package com.okestro.boardapi.repo;

import com.okestro.boardapi.dto.post.response.PostResponse;
import com.okestro.boardapi.model.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT new com.okestro.boardapi.dto.post.response.PostResponse(p.id, p.title, p.userId, SIZE(p.commentEntities)) " +
            "FROM PostEntity p " +
            "LEFT JOIN p.commentEntities " +
            "GROUP BY p.id " +
            "ORDER BY p.id DESC",
            countQuery = "SELECT COUNT(p) FROM PostEntity p")
    Page<PostResponse> findAllWithCommentCount(Pageable pageable);
}
