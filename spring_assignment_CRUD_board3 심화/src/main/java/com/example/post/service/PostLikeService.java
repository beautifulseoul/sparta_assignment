package com.example.post.service;

import com.example.post.dto.ResponseDto;
import com.example.post.entity.*;
import com.example.post.exception.CustomException;
import com.example.post.exception.ErrorCode;
import com.example.post.exception.SuccessCode;
import com.example.post.repository.PostLikeRepository;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseDto postLike(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND));

        if (postLikeRepository.findByUserIdAndPostId(user.getId(), post.getId()).isEmpty()) {
            postLikeRepository.save(new PostLike(post, user));
            return new ResponseDto(SuccessCode.LIKE);
        } else {
            postLikeRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
            return new ResponseDto(SuccessCode.CANCEL_LIKE);
        }
    }
}
