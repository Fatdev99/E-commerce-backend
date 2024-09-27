package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface roleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}
