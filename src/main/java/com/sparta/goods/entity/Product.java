package com.sparta.goods.entity;

import com.sparta.goods.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private String category;

    public Product(ProductRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.count = requestDto.getCount();
        this.introduction = requestDto.getIntroduction();
        this.category = requestDto.getCategory();
    }
}
