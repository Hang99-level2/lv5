package com.sparta.goods.entity;

import com.sparta.goods.dto.UserSignupRequestDto;
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

    public User(UserSignupRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.gender = requestDto.getGender();
        this.number = requestDto.getNumber();
        this.address = requestDto.getAddress();
        if(requestDto.getRole().equals("ADMIN")){
            this.role =  UserRoleEnum.ADMIN;
        }else{
            this.role =  UserRoleEnum.USER;
        }
    }
}