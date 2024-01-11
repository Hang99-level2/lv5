package com.sparta.goods.product.controller;

import com.sparta.goods.product.dto.ProductRequestDto;
import com.sparta.goods.product.dto.ProductResponseDto;
import com.sparta.goods.product.service.ProductService;
import com.sparta.goods.security.UserDetailsImpl;
import com.sparta.goods.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null || userDetails.getUser().getRole().equals(UserRoleEnum.USER)){
            throw new AccessDeniedException("권한이 없습니다.");
        }
        return productService.createProduct(requestDto);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("isAsc") Boolean isAsc){
        return productService.getProducts(page - 1, size, sortBy, isAsc).stream().toList();
    }

    @GetMapping("/products/{productId}")
    public ProductResponseDto getProductById(@PathVariable Long productId){
        return new ProductResponseDto(productService.getProductById(productId));
    }
}
