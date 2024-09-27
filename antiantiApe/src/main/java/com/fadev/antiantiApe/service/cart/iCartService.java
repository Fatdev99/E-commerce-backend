package com.fadev.antiantiApe.service.cart;

import com.fadev.antiantiApe.model.Cart;
import com.fadev.antiantiApe.model.User;

import java.math.BigDecimal;

public interface iCartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
