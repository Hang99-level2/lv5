package com.sparta.goods.repository;

import com.sparta.goods.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserId(long id);

    Cart findByUserIdAndProductId(Long userId, Long productId);
}