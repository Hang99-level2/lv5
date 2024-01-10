package com.sparta.goods.controller;

import com.sparta.goods.dto.CartPriceResponseDto;
import com.sparta.goods.dto.CartRequestDto;
import com.sparta.goods.dto.CartResponseDto;
import com.sparta.goods.dto.UpdateCartProductRequestDto;
import com.sparta.goods.entity.Cart;
import com.sparta.goods.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    @PostMapping("/carts/products")
    public List<CartResponseDto> addProductToCart(@RequestBody List<CartRequestDto> cartRequestDtoList,
                                                  HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        return cartService.addProductToCart(userId,cartRequestDtoList);
    }

    @GetMapping("/carts/products")
    public CartPriceResponseDto getCartProducts(HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        return cartService.getProducts(userId);
    }

    @PutMapping("/carts/products/{productId}")
    public CartResponseDto updateCartProduct(@PathVariable Long productId,
                                             @RequestBody UpdateCartProductRequestDto requestDto,
                                             HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        return cartService.updateCartProduct(userId,productId,requestDto);
    }

    @DeleteMapping("/carts/products/{productId}")
    public Long deleteCartProduct(@PathVariable Long productId,
                                  HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        return cartService.deleteCartProduct(userId,productId);
    }
}
