package com.example.board.entity;

import com.example.board.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity                                                   //클래스를 테이블과 매핑시키기 위해 필수로 사용되는 어노테이션
@Getter
@NoArgsConstructor                                        //기본 생성자를 생성해줌
public class Board extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)             //하나씩 증가시키는것! 키값으로 설정
    @Column
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private String username;
    @Column
    private Long userId;

    //생성자
    public Board(BoardRequestDto boardRequestDto, Long userid, String username){
        this.title=boardRequestDto.getTitle();
        this.content=boardRequestDto.getContent();
        this.userId = userid;
        this.username = username;
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }
}
