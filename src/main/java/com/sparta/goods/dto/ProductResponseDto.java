package com.sparta.goods.dto;

import com.sparta.goods.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String message;

    public ProductResponseDto(String message) {
        this.message =message;
    }
}
