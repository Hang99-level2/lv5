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
    private Long id;

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

    public Product(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.count = productRequestDto.getCount();
        this.introduction = productRequestDto.getIntroduction();
        this.category = productRequestDto.getCategory();
    }
}
