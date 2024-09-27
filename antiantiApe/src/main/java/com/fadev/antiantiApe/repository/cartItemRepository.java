package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
