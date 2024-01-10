package com.sparta.goods.controller;

import com.sparta.goods.dto.ProductRequestDto;
import com.sparta.goods.dto.ProductResponseDto;
import com.sparta.goods.entity.Product;
import com.sparta.goods.entity.UserRoleEnum;
import com.sparta.goods.jwt.JwtUtil;
import com.sparta.goods.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto, HttpServletRequest req){
        if (req.getAttribute("role").equals(UserRoleEnum.USER)){
            return new ProductResponseDto("실패");
        }
        return productService.createProduct(productRequestDto);
    }
}
