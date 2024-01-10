package com.sparta.goods.controller;

import com.sparta.goods.dto.ProductRequestDto;
import com.sparta.goods.dto.ProductResponseDto;
import com.sparta.goods.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto){
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
        return productService.getProductById(productId);
    }
}
