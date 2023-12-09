package com.example.eliminatoriasapi.security.repositories;

import com.example.eliminatoriasapi.security.entities.Role;
import com.example.eliminatoriasapi.security.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);

}
