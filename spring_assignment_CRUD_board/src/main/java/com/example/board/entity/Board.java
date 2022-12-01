package com.example.board.entity;

import com.example.board.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity                                                   //클래스를 테이블과 매핑시키기 위해 필수로 사용되는 어노테이션
@Getter
@NoArgsConstructor                                        //기본 생성자를 생성해줌
public class Board extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //하나씩 증가시키는것! 키값으로 설정
    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;


    //생성자
    public Board(BoardRequestDto boardRequestDto){
        this.author=boardRequestDto.getAuthor();
        this.title=boardRequestDto.getTitle();
        this.content=boardRequestDto.getContent();
        this.password=boardRequestDto.getPassword();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.author = boardRequestDto.getAuthor();
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }


//    메서드
//    public void save(BoardRequestDto boardRequestDto){
//        this.title=boardRequestDto.getTitle();
//        this.author=boardRequestDto.getAuthor();
//        this.content=boardRequestDto.getContent();
//        this.password=boardRequestDto.getPassword();
//    }
}
