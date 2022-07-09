package com.healthcare_service.controller;

import com.healthcare_service.entity.role.ERole;
import com.healthcare_service.entity.role.Role;
import com.healthcare_service.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/role")
    private Role addRole(String id){

       return roleService.createRole(ERole.valueOf(id));
    }

    @GetMapping("/roles")
    private List<Role> findRoles(){
        return roleService.getRoles();
    }
}
