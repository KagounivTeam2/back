package com.kagouniv.kagouniv_back.repository;

import com.kagouniv.kagouniv_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);

    Optional<User> findByRefreshToken(String refreshToken);
}
