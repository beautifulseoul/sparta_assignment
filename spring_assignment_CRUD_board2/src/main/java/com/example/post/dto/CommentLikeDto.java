package com.example.post.dto;

import com.example.post.entity.Comment;
import com.example.post.entity.User;
import javax.persistence.*;

public class CommentLikeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //생성자
    public CommentLikeDto(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }
}
