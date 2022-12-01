package com.example.test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private Long id;
    private String name;
    private String email;
    private String pw;
}
