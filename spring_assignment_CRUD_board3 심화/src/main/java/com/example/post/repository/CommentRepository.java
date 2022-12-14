package com.example.post.repository;

import com.example.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface CommentRepository extends JpaRepository<Comment, Long> {


    Optional<Comment> findById(Long id);
    Optional<Comment> findByIdAndUserId(Long id, Long userId);
}
