package com.example.post.service;

import com.example.post.dto.ResponseDto;
import com.example.post.entity.Comment;
import com.example.post.entity.CommentLike;
import com.example.post.entity.User;
import com.example.post.exception.CustomException;
import com.example.post.exception.ErrorCode;
import com.example.post.exception.SuccessCode;
import com.example.post.repository.CommentLikeRepository;
import com.example.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ResponseDto commentLike(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_EXIST_COMMENT));

        if (commentLikeRepository.findByUserIdAndCommentId(user.getId(), comment.getId()).isEmpty()) {
            commentLikeRepository.save(new CommentLike(comment, user));
            return new ResponseDto(SuccessCode.LIKE);
        } else {
            commentLikeRepository.deleteByUserIdAndCommentId(user.getId(), comment.getId());
            return new ResponseDto(SuccessCode.CANCEL_LIKE);
        }
    }
}
