package com.okestro.boardapi.repo;

import com.okestro.boardapi.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
