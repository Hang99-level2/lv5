package com.sparta.goods.dto;

import com.sparta.goods.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String name;
    private Long price;
    private Long count;
    private String introduction;
    private String category;

    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = product.getCount();
        this.introduction = product.getIntroduction();
        this.category = product.getCategory();
    }
}
