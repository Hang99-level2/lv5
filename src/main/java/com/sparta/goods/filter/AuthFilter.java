package com.sparta.goods.filter;


import com.sparta.goods.user.entity.User;
import com.sparta.goods.util.JwtUtil;
import com.sparta.goods.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(1)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    private void createJsonResponse(ServletResponse res, int status, String errorMessage) throws IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.print("{\"code\":" + status + ",\"message\":\"" + errorMessage + "\"}");
        out.flush();
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String url = req.getRequestURI();

        //TODO::인증 필요 없는 부분 추가
        //회원가입 로그인
        if (StringUtils.hasText(url) && (url.startsWith("/api/users"))) {
            chain.doFilter(req, res);
            //강의 조회
        } else if (StringUtils.hasText(url) && (url.startsWith("/api/courses") && req.getMethod().equals("GET"))) {
            chain.doFilter(req, res);
        }
        //인증 필요한 부분
        else {
            String tokenValue = jwtUtil.getTokenFromRequest(req);
            //인증받기 or 권한없음
            if (StringUtils.hasText(tokenValue)) {
                String token = jwtUtil.substringToken(tokenValue);
                if (!jwtUtil.validateToken(token)) {
                    createJsonResponse(res, HttpStatus.FORBIDDEN.value(), "토큰 에러");
                    return;
                }

                //여기서 저장한 값 => 유저 정보
                Claims info = jwtUtil.getUserInfoFromToken(token);
                User user = userRepository.findByEmail(info.getSubject()).orElseThrow(()-> new IllegalArgumentException("이메일이 없어요"));
                req.setAttribute("userId", user.getId());
                req.setAttribute("role", user.getRole());
                chain.doFilter(req, res);
            } else {
                createJsonResponse(res, HttpStatus.FORBIDDEN.value(), "권한 없음");
            }
        }

    }
}