package com.sparta.goods.cart.repository;

import com.sparta.goods.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(long userId);

    Cart findByUserIdAndProductId(Long userId, Long productId);
}
