package com.example.eliminatoriasapi.security.services;

import com.example.eliminatoriasapi.security.entities.Role;
import com.example.eliminatoriasapi.security.enums.RoleList;
import com.example.eliminatoriasapi.security.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Optional<Role> getByRoleName(RoleList roleName){
        return roleRepository.findByRoleName(roleName);
    }


}
