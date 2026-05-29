package com.juliodev.ecommerce.repositories;

import com.juliodev.ecommerce.model.AppRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.juliodev.ecommerce.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.roleName = :role")
    Page<User> findByRoleName(@Param("role") AppRole role, Pageable pageable);
}
