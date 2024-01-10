package com.sparta.goods.service;

import com.sparta.goods.dto.ProductRequestDto;
import com.sparta.goods.dto.ProductResponseDto;
import com.sparta.goods.entity.Product;
import com.sparta.goods.entity.User;
import com.sparta.goods.jwt.JwtUtil;
import com.sparta.goods.repository.ProductRepository;
import com.sparta.goods.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final JwtUtil jwtUtil;
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);
        productRepository.save(product);
        return new ProductResponseDto("성공");
    }
}
