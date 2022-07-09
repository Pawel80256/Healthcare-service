package com.healthcare_service.service.role;

import com.healthcare_service.entity.role.ERole;
import com.healthcare_service.entity.role.Role;
import com.healthcare_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository repository;

    public Role createRole(ERole id){
        var role = new Role(id);
        repository.save(role);
        return role;
    }

    public List<Role> getRoles(){
        return repository.findAll();
    }
}
