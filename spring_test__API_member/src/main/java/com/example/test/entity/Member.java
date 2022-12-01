package com.example.test.entity;

import com.example.test.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity                                             //클래스를 테이블과 매핑시키기 위해 필수로 사용되는 어노테이션
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String pw;

    public Member(MemberRequestDto memberRequestDto){
        this.id=memberRequestDto.getId();
        this.name=memberRequestDto.getName();
        this.email=memberRequestDto.getEmail();
        this.pw=memberRequestDto.getPw();
    }
}
