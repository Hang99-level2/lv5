package com.sparta.goods.controller;

import com.sparta.goods.dto.LoginRequestDto;
import com.sparta.goods.dto.LoginResponseDto;
import com.sparta.goods.dto.UserSignupRequestDto;
import com.sparta.goods.dto.UserSignupResponseDto;
import com.sparta.goods.jwt.JwtUtil;
import com.sparta.goods.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private  final JwtUtil jwtUtil;

    @PostMapping("/users")
    public UserSignupResponseDto UserSignUp(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto){
        return userService.UserSignUp(userSignupRequestDto);
    }

    @PostMapping("/users/login")
    public LoginResponseDto Login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res){
        String token = userService.Login(loginRequestDto,res);
        if(token ==null){
            return new LoginResponseDto("로그인 실패");
        }
        return new LoginResponseDto("로그인 성공");
    }
}
