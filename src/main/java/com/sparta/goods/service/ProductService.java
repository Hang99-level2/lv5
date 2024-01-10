package com.sparta.goods.service;

import com.sparta.goods.dto.ProductRequestDto;
import com.sparta.goods.dto.ProductResponseDto;
import com.sparta.goods.entity.Product;
import com.sparta.goods.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        productRepository.save(product);
        return new ProductResponseDto(product);
    }

    public Page<ProductResponseDto> getProducts(int page, int size, String sortBy, Boolean isAsc) {
        //여기서 유효성검증
        Sort.Direction direction = isAsc? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductResponseDto::new);
    }

    public ProductResponseDto getProductById(Long productId) {
       Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("아이디에 해당하는 상품이 없습니다."));
       return new ProductResponseDto(product);
    }
}
