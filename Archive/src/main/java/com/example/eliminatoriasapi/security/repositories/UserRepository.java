package com.example.eliminatoriasapi.security.repositories;

import com.example.eliminatoriasapi.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);

}
