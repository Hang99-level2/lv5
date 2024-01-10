package com.sparta.goods.service;


import com.sparta.goods.dto.LoginRequestDto;
import com.sparta.goods.dto.UserSignupRequestDto;
import com.sparta.goods.dto.UserSignupResponseDto;
import com.sparta.goods.entity.User;
import com.sparta.goods.jwt.JwtUtil;
import com.sparta.goods.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserSignupResponseDto UserSignUp(UserSignupRequestDto userSignupRequestDto) {
        String email = userSignupRequestDto.getEmail();
        userSignupRequestDto.setPassword(passwordEncoder.encode(userSignupRequestDto.getPassword()));
        User checkEmail = userRepository.findByEmail(email);
        if(checkEmail !=null){
            throw new IllegalArgumentException("email이 중복되었습니다.");
        }
        User user = new User(userSignupRequestDto);
        userRepository.save(user);
        return new UserSignupResponseDto(user);
    }

    public String Login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email =loginRequestDto.getEmail();
        String passwrod =loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email);

        if(!passwordEncoder.matches(passwrod,user.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtUtil.createToken(email,user.getRole());
        jwtUtil.addJwtToCookie(token,res);
        return token;
    }
}
