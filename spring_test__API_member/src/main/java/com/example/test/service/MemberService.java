package com.example.test.service;

import com.example.test.dto.MemberRequestDto;
import com.example.test.dto.MemberResponseDto;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


//     한 회원의 userId가 주었을때 회원 정보를 조회하는 기능 (@param id 사용하기)!!!
    public MemberResponseDto findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("회원 상세 조회 실패")
        );
        return new MemberResponseDto(member);
    }


    //     회원의 전체 목록을 조회하는 기능!!!
    public List<MemberResponseDto> findAllMember() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDto = new ArrayList<>();
        for(Member member : memberList){
            MemberResponseDto memberResponseDto1 = new MemberResponseDto(member);
            memberResponseDto.add(memberResponseDto1);
        }
        return memberResponseDto;
    }


    //     조회 잘 되는지 test를 위한 post기능 추가 구현!!!
    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto);
        memberRepository.save(member);
        return new MemberResponseDto(member);
    }
}
