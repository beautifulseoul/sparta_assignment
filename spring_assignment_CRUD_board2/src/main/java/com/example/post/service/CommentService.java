package com.example.post.service;

import com.example.post.dto.*;
import com.example.post.entity.Post;
import com.example.post.entity.Comment;
import com.example.post.entity.User;
import com.example.post.entity.UserRoleEnum;
import com.example.post.exception.CustomException;
import com.example.post.exception.ErrorCode;
import com.example.post.exception.SuccessCode;
import com.example.post.repository.PostRepository;
import com.example.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto saveComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND)
        );

        Comment comment = new Comment(commentRequestDto, post, user);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    public CommentResponseDto updateComment(Long id, Long commentId, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND));

        Comment comment;
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            // ADMIN 권한일 때
            comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NO_EXIST_COMMENT));
        } else {
            // User 권한일 때
            comment = commentRepository.findByIdAndUserId(commentId, user.getId()).orElseThrow(() -> new CustomException(ErrorCode.NO_MODIFY_COMMENT));

        }
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    public ResponseDto deleteComment(Long id, Long commentId, User user) {
        postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND));

        Comment comment;
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            // ADMIN 권한일 때
            comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NO_EXIST_COMMENT));
        } else {
            // User 권한일 때
            comment = commentRepository.findByIdAndUserId(commentId, user.getId()).orElseThrow(() -> new CustomException(ErrorCode.NO_DELETE_COMMENT));
        }

        commentRepository.delete(comment);
        return new ResponseDto(SuccessCode.DELETE_COMMENT);
    }
}