package com.sparta.goods.cart.dto;

import com.sparta.goods.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class CartPriceResponseDto {
    private List<Product> productList;
    private Long allPrice;
    public CartPriceResponseDto(){
        this.allPrice = 0L;
        this.productList = new ArrayList<>();
    }
}
