package com.sparta.goods.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Long pCount;

    public Cart(User user, Product product,Long pCount){
        this.user = user;
        this.product = product;
        this.pCount = pCount;
    }

    public void updateProductCount(Long productCount) {
        this.pCount = productCount;
    }
}