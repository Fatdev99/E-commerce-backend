package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderItemRepository extends JpaRepository<OrderItem, Long> {

}
