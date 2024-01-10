package com.sparta.goods.dto;

import com.sparta.goods.entity.User;
import com.sparta.goods.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {

    private String email;
    private String password;
    private String role;
    private String gender;
    private String number;
    private String address;

    public SignupResponseDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.number = user.getNumber();
        this.address = user.getAddress();
        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            this.role = "ADMIN";
        }
        else{
            this.role = "USER";
        }
    }
}
