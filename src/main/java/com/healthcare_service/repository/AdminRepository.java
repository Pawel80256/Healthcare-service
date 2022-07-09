package com.healthcare_service.repository;

import com.healthcare_service.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByUsername(String username);
    Boolean existsByUsername(String username);
}
