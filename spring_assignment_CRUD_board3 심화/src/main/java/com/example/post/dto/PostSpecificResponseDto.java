package com.example.post.dto;

import com.example.post.entity.Post;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostSpecificResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdat;
    private LocalDateTime modifiedat;
    private String username;
    private int postLikeCnt;
    List<CommentResponseDto> commentList;

    //생성자
    public PostSpecificResponseDto(Post post, List<CommentResponseDto> commentList){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdat = post.getCreatedAt();
        this.modifiedat = post.getModifiedAt();
        this.username = post.getUsername();
        this.commentList = commentList;
        this.postLikeCnt = post.getPostLikes().size();
    }
}
