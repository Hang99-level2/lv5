package com.sparta.goods.dto;

import com.sparta.goods.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupResponseDto {
    private String email;
    public UserSignupResponseDto(User user){
        this.email =user.getEmail();
    }
}
