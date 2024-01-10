package com.sparta.goods.dto;

import com.sparta.goods.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
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