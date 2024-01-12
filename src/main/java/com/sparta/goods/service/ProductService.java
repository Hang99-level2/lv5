package com.sparta.goods.service;

import com.sparta.goods.dto.ProductRequestDto;
import com.sparta.goods.dto.ProductResponseDto;
import com.sparta.goods.dto.ProductSelectResponseDto;
import com.sparta.goods.entity.Product;
import com.sparta.goods.entity.User;
import com.sparta.goods.entity.UserRoleEnum;
import com.sparta.goods.jwt.JwtUtil;
import com.sparta.goods.repository.ProductRepository;
import com.sparta.goods.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto);
        productRepository.save(product);
        return new ProductResponseDto("성공");
    }

    public Page<ProductSelectResponseDto> getProduct(int page, int size , String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC :Sort.Direction.DESC;
        Sort sort = Sort.by(direction,sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductSelectResponseDto::new);
    }

    public Product selectProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("상품이 없습니다."));
    }
}
