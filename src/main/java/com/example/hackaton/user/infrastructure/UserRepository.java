package com.example.hackaton.user.infrastructure;

import com.example.hackaton.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository <T extends User> extends JpaRepository<T, Integer> {
    Optional<T> findByEmail(String email);
}
