package com.sparta.goods.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private Long price;
    private Long count;
    private String introduction;
    private String category;
}
