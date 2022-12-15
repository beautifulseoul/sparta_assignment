package com.example.post.dto;

import com.example.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdat;
    private LocalDateTime modifiedat;
    private String username;
    private int postLikeCnt;


    //생성자
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdat = post.getCreatedAt();
        this.modifiedat = post.getModifiedAt();
        this.username = post.getUsername();
        this.postLikeCnt = post.getPostLikes().size();
    }
}
