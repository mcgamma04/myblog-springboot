package com.stringcodeltd.myblogapp.repository;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.stringcodeltd.myblogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
  Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
