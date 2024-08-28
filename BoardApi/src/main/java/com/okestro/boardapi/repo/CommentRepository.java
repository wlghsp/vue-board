package com.okestro.boardapi.repo;

import com.okestro.boardapi.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
