package com.okestro.boardapi.repo;

import com.okestro.boardapi.model.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByTitle(String title);

    Page<PostEntity> findAllByOrderByIdDesc(Pageable pageable);
}
