package com.example.post.repository;

import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();                     /// <>안의 테이블과 연결된다고 보면 됨!
    Optional<Post> findByIdAndUserId(Long userId, Long id);
}
