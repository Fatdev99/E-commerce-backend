package com.fadev.antiantiApe.repository;

import com.fadev.antiantiApe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
