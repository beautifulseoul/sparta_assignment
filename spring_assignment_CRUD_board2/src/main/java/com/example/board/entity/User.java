package com.example.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 10, message ="아이디의 길이는 4자 이상, 10자 이하이어야 합니다.")
    @Pattern(regexp = "[a-z0-9]*$", message = "아이디는 소문자와 숫자로만 구성되어야 합니다.")
    private String username;

    @Column(nullable = false)
    @Size(min=8, max= 15, message="비밀번호의 길이는 8자에서 15자 사이입니다")
    @Pattern(regexp = "[a-zA-Z0-9]*$",message = "비밀번호 형식이 일치하지 않습니다")
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;

    }
}
