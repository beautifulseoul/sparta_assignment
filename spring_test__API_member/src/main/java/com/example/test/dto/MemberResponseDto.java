package com.example.test.dto;

import com.example.test.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String pw;


    //생성자
    public MemberResponseDto(Member member){
        this.id=member.getId();
        this.name=member.getName();
        this.email=member.getEmail();
        this.pw=member.getPw();
    }
}
