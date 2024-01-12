package com.sparta.goods.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {
    private Long productId;
    private Long productCount;
}