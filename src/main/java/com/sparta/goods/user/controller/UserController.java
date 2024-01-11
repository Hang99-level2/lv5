package com.sparta.goods.user.controller;


import com.sparta.goods.user.dto.SignupRequestDto;
import com.sparta.goods.user.dto.SignupResponseDto;
import com.sparta.goods.user.service.UserService;
import com.sparta.goods.util.JwtUtil;
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
}

