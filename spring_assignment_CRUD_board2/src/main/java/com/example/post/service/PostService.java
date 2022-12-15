//메서드가 들어가는 파일

package com.example.post.service;

import com.example.post.dto.*;
import com.example.post.entity.Post;
import com.example.post.entity.Comment;
import com.example.post.entity.User;
import com.example.post.entity.UserRoleEnum;
import com.example.post.exception.CustomException;
import com.example.post.exception.ErrorCode;
import com.example.post.exception.SuccessCode;
import com.example.post.jwt.JwtUtil;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;                 // 싱글톤이어야하기 때문!
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final User user;

    //포스팅 게시
    public PostResponseDto savePost(PostRequestDto postRequestDto, User user) {
        Post post = postRepository.save(new Post(postRequestDto, user));        //데이터베이스에 연결해서 저장하려면 @Entity어노테이션이 걸려져있는 Post클래스를 인스턴스로 만들어서 그 값을사용해서 저장해야 함. 그렇기때문에 board 객체를 만들어주고 생성자를 사용해서 값들을 넣어줘야 함.
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    //전체 포스팅 조회
    public List<PostSpecificResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostSpecificResponseDto> postSpecificResponseDtoList = new ArrayList<>();

        for (Post post : postList) {
            List<CommentResponseDto> commentList = new ArrayList<>();
            for (Comment comment : post.getCommentList()) {
                commentList.add(new CommentResponseDto(comment));
            }
            PostSpecificResponseDto postSpecificDto = new PostSpecificResponseDto(post, commentList);
            postSpecificResponseDtoList.add(postSpecificDto);
        }
        return postSpecificResponseDtoList;
    }

    //포스팅 하나 조회
    public PostSpecificResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND));
        List<CommentResponseDto> commentList = new ArrayList<>();
        for (Comment comment : post.getCommentList()) {
            commentList.add(new CommentResponseDto(comment));
        }
        return new PostSpecificResponseDto(post, commentList);
    }

    @Transactional
    public PostSpecificResponseDto putPost(Long id, PostRequestDto postRequestDto, User user) {
        Post post;
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            // 관리자 권한이 ADMIN인 경우
            post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.ALREADY_EXIST_USERNAME));
            post.update(postRequestDto);
            List<CommentResponseDto> commentList = new ArrayList<>();
            for (Comment comment : post.getCommentList()) {
                commentList.add(new CommentResponseDto(comment));
            }
            return new PostSpecificResponseDto(post, commentList);
        } else {
            // 사용자 권한이 USER일 경우
            post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND));
            post.update(postRequestDto);
            List<CommentResponseDto> commentList = new ArrayList<>();             //이부분 수정필요! 이진님께서 짧게 줄일수이다고 알려주심.
            for(Comment comment : post.getCommentList()){
                commentList.add(new CommentResponseDto(comment));
            }
            return new PostSpecificResponseDto(post, commentList);
        }
    }

    // 게시글 삭제 기능
    @Transactional
    public ResponseDto deletePost(Long id, User user) {
        Post post;
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            // ADMIN 권한일 때
            post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NO_POST_FOUND));
            postRepository.delete(post);
        } else {
            // USER 권할일 때
            post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new CustomException(ErrorCode.NO_DELETE_POST));
            postRepository.delete(post);
        }
        return new ResponseDto(SuccessCode.DELETE_POST);
    }

}
