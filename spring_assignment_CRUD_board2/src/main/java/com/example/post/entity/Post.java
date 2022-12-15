package com.example.post.entity;

import com.example.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OrderBy("createdAt desc")
    private List<Comment> commentList= new ArrayList<>();


    //생성자
    public Post(PostRequestDto postRequestDto, User user){
        this.title=postRequestDto.getTitle();
        this.content=postRequestDto.getContent();
        this.username=user.getUsername();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
    }
}
