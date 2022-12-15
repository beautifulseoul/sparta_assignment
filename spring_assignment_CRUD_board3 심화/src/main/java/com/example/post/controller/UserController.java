package com.example.post.controller;

import com.example.post.dto.LoginRequestDto;
import com.example.post.dto.ResponseDto;
import com.example.post.dto.SignupRequestDto;
import com.example.post.exception.SuccessCode;
import com.example.post.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입 기능 (ADMIN_TOKEN 넣을시 ADMIN 권한 가짐. ADMIN_TOKEN 안 넣으면 일반 USER 권한)
    @PostMapping("signup")
    public ResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new ResponseDto(SuccessCode.SIGN_UP);
    }

    // 로그인 기능
    @PostMapping("login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
        return new ResponseDto(SuccessCode.LOG_IN);
    }
}
