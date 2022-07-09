package com.healthcare_service.repository;

import com.healthcare_service.entity.role.ERole;
import com.healthcare_service.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, ERole> {

}
