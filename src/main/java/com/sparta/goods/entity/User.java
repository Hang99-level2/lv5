package com.sparta.goods.entity;

import com.sparta.goods.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private UserRoleEnum role;

    public User(SignupRequestDto signupRequestDto) {
        this.email = signupRequestDto.getEmail();
        this.password = signupRequestDto.getPassword();
        this.gender = signupRequestDto.getGender();
        this.number = signupRequestDto.getNumber();
        this.address = signupRequestDto.getAddress();
        if(signupRequestDto.getRole().equals("USER")){
            this.role = UserRoleEnum.USER;
        }
        else if(signupRequestDto.getRole().equals("ADMIN")){
            this.role = UserRoleEnum.ADMIN;
        }
    }
}