package com.example.test.controller;

import com.example.test.dto.MemberRequestDto;
import com.example.test.dto.MemberResponseDto;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


//     한 회원의 userId가 주었을때 회원 정보를 조회하는 API!!!
//     @param id 사용하기!!
    @GetMapping("/member/{id}")
    public MemberResponseDto getMemberInfo(@RequestParam Long id) {
        return memberService.findMember(id);
    }


//     회원의 전체 목록을 조회하는 API!!!!
    @GetMapping("/member")
    public List<MemberResponseDto> getMemberList() {
       return memberService.findAllMember();
    }


//     조회 잘 되는지 test를 위한 post기능 추가 구현.  Post API!!!
    @Transactional
    @PostMapping("/member")
    public MemberResponseDto saveMember(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.saveMember(memberRequestDto);
    }
}
