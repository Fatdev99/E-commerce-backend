package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface imageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
