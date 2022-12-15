package com.example.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Post_ID", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;


    //생성자
    public PostLike(Post post, User user){
        this.post = post;
        this.user = user;
    }
}
