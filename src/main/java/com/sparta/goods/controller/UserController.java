package com.sparta.goods.controller;

import com.sparta.goods.dto.LoginResponseDto;
import com.sparta.goods.dto.SignupRequestDto;
import com.sparta.goods.dto.SignupResponseDto;
import com.sparta.goods.jwt.JwtUtil;
import com.sparta.goods.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/users")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        SignupResponseDto signupResponseDto = userService.signup(signupRequestDto);
        return ResponseEntity.ok(signupResponseDto);
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String token = userService.login(loginRequestDto);
        jwtUtil.addJwtToCookie(token, res);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}

