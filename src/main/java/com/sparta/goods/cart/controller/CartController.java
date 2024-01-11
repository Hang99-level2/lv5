package com.sparta.goods.cart.controller;

import com.sparta.goods.cart.dto.CartPriceResponseDto;
import com.sparta.goods.cart.dto.CartRequestDto;
import com.sparta.goods.cart.dto.CartResponseDto;
import com.sparta.goods.cart.dto.UpdateCartProductRequestDto;
import com.sparta.goods.cart.service.CartService;
import com.sparta.goods.security.UserDetailsImpl;
import com.sparta.goods.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    @PostMapping("/carts/products")
    public List<CartResponseDto> addProductToCart(@RequestBody List<CartRequestDto> cartRequestDtoList,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return cartService.addProductToCart(user.getId(),cartRequestDtoList);
    }

    @GetMapping("/carts/products")
    public CartPriceResponseDto getCartProducts(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return cartService.getProducts(user.getId());
    }

    @PutMapping("/carts/products/{productId}")
    public CartResponseDto updateCartProduct(@PathVariable Long productId,
                                             @RequestBody UpdateCartProductRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return cartService.updateCartProduct(user.getId(),productId,requestDto);
    }

    @DeleteMapping("/carts/products/{productId}")
    public Long deleteCartProduct(@PathVariable Long productId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return cartService.deleteCartProduct(user.getId(),productId);
    }
}
