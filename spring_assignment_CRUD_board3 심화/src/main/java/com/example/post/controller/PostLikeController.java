package com.example.post.controller;

import com.example.post.dto.ResponseDto;
import com.example.post.security.UserDetailsImpl;
import com.example.post.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    // 게시글 `좋아요` 반영
    @PostMapping("/api/posts/like/{id}")
    public ResponseDto postLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return postLikeService.postLike(id, userDetailsImpl.getUser());
    }
}
