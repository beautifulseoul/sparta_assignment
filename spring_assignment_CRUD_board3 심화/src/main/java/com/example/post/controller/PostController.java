package com.example.post.controller;

import com.example.post.dto.PostResponseDto;
import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostSpecificResponseDto;
import com.example.post.dto.ResponseDto;
import com.example.post.security.UserDetailsImpl;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 포스팅하기
    @PostMapping("/posts")
    public PostResponseDto savePost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {     //객체형식으로 넘어오기 때문에 requestbody씀
        return postService.savePost(postRequestDto, userDetailsImpl.getUser());
    }

    // 모든 포스팅가져오기
    @GetMapping("/posts")
    public List<PostSpecificResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 포스팅 하나만 상세조회
    @GetMapping("/posts/{id}")
    public PostSpecificResponseDto getpost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 포스팅 수정
    @PutMapping("/posts/{id}")
    public PostSpecificResponseDto putPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return postService.putPost(id, postRequestDto, userDetailsImpl.getUser());
    }

    // 포스팅 삭제하기
    @DeleteMapping("/posts/{id}")
    public ResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return postService.deletePost(id, userDetailsImpl.getUser());
    }
}
