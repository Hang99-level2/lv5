package com.sparta.goods.interceptor;

import com.sparta.goods.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return true;
        }

        UserRoleEnum role = (UserRoleEnum) request.getAttribute("role");

        if(!role.equals((UserRoleEnum.ADMIN))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "관리자 권한이 필요합니다.");
        }
        return true;
    }
}