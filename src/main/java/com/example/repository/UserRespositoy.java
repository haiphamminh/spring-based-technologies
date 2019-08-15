package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespositoy extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
