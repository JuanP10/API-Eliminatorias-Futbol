package com.example.eliminatoriasapi.security.services;

import com.example.eliminatoriasapi.security.entities.User;
import com.example.eliminatoriasapi.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public boolean existByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
    public void save(User user){
        userRepository.save(user);
    }

}
