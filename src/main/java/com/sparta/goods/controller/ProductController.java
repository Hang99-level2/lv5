package com.sparta.goods.controller;

import com.sparta.goods.dto.ProductRequestDto;
import com.sparta.goods.dto.ProductResponseDto;
import com.sparta.goods.dto.ProductSelectResponseDto;
import com.sparta.goods.entity.Product;
import com.sparta.goods.entity.UserRoleEnum;
import com.sparta.goods.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto, HttpServletRequest req) {
        if (req.getAttribute("role").equals(UserRoleEnum.USER)) {
            return new ProductResponseDto("실패");
        }
        return productService.createProduct(productRequestDto);
    }
    @GetMapping("/products")
    public List<ProductSelectResponseDto> getProduct(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc){
        List<ProductSelectResponseDto> productList = (productService.getProduct(page-1,size,sortBy,isAsc)).getContent();
        return productList;
    }
    @GetMapping("/products/{productId}")
    public Product selectProduct(@PathVariable Long productId){
        return productService.selectProduct(productId);
    }
}
