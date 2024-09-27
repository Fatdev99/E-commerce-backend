package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface orderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
