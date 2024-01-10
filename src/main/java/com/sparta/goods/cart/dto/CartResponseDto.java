package com.sparta.goods.cart.dto;

import com.sparta.goods.cart.entity.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {
    private Long productId;
    private Long pCount;

    public CartResponseDto(Cart cart){
        this.productId = cart.getProduct().getId();
        this.pCount = cart.getPCount();
    }
}
