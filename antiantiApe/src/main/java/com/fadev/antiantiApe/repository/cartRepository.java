package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
