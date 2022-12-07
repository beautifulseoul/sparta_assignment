package com.example.board.repository;

import com.example.board.entity.Board;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();                     /// <>안의 테이블과 연결된다고 보면 됨!
    Optional<Board> findByIdAndUserId(Long id, Long userId);
}
