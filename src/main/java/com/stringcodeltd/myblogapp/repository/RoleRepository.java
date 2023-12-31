package com.stringcodeltd.myblogapp.repository;

import com.stringcodeltd.myblogapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role>findByName(String name);
//    8944D73AB
}
