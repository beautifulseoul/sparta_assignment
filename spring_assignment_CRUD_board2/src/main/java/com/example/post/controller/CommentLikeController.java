package com.example.post.controller;

import com.example.post.dto.ResponseDto;
import com.example.post.security.UserDetailsImpl;
import com.example.post.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    // 댓글 '좋아요'기능
    @PostMapping("/api/comments/like/{id}")
    public ResponseDto commentLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return commentLikeService.commentLike(id, userDetailsImpl.getUser());
    }
}
