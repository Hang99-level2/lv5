package com.sparta.goods.dto;

import com.sparta.goods.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {
    @Email(message ="올바른 이메일 형식이 아닙니다")
    private String email;
    @Size(min =8,max=15,message = "비밀번호는 8자 이상 15자 이하로 설정해주세요.")
    @Pattern(regexp ="^[a-zA-Z0-9\\W]+$",message = "비밀번호는 알파벳 대소문자, 숫자, 특수문자 이외의 문자를 포함 하면 안됩니다.")
    private String password;
    private String gender;
    private String number;
    private String address;
    private String role;
}
