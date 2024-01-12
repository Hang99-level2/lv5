package com.sparta.goods.dto;

import com.sparta.goods.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSelectResponseDto {
    private String name;
    private Long price;
    public ProductSelectResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
