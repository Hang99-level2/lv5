package com.sparta.goods.service;

import com.sparta.goods.dto.CartPriceResponseDto;
import com.sparta.goods.dto.CartRequestDto;
import com.sparta.goods.dto.CartResponseDto;
import com.sparta.goods.dto.UpdateCartProductRequestDto;
import com.sparta.goods.entity.Cart;
import com.sparta.goods.entity.Product;
import com.sparta.goods.entity.User;
import com.sparta.goods.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public List<CartResponseDto> addProductToCart(Long userId, List<CartRequestDto> cartRequestDtoList) {
        //아이디 찾기
        User user = userService.getUserById(userId);
        List<Cart> newCartList = new ArrayList<>();
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());
        for (CartRequestDto cartRequestDto : cartRequestDtoList) {
            for (Cart cart : cartList) {
                if(cart.getProduct().getId()==cartRequestDto.getProductId()){
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                }
            }
            Product product = productService.getProductById(cartRequestDto.getProductId());
            newCartList.add(new Cart(user,product,cartRequestDto.getProductCount()));
        }
        //모든 상품이 장바구니에 존재하지 않음
        cartRepository.saveAll(newCartList);
        return newCartList.stream().map(CartResponseDto::new).toList();
    }
    @Transactional
    public CartPriceResponseDto getProducts(Long userId) {
        User user = userService.getUserById(userId);
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());
        CartPriceResponseDto cartPriceResponseDto = new CartPriceResponseDto();
        Long price = 0L;
        for (Cart cart : cartList) {
            price += cart.getProduct().getPrice() * cart.getPCount();
            cartPriceResponseDto.getProductList().add(cart.getProduct());
        }
        cartPriceResponseDto.setAllPrice(cartPriceResponseDto.getAllPrice()+price);
        return cartPriceResponseDto;
    }
    @Transactional
    public CartResponseDto updateCartProduct(Long userId, Long productId, UpdateCartProductRequestDto requestDto) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId,productId);
        cart.updateProductCount(requestDto.getProductCount());
        return new CartResponseDto(cart);
    }
    @Transactional
    public Long deleteCartProduct(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId,productId);
        Long cartId = cart.getId();
        cartRepository.delete(cart);
        return cartId;
    }
}