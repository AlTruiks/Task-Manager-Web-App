package com.project.taskmanager.service;

import com.project.taskmanager.model.User;
import com.project.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User GetUser(Long id) {
        return userRepository.getReferenceById(id);
    }
    public Optional<User> findUserByLoginAndPassword(String nickname, String password) {
        return userRepository.findUserByLoginAndPassword(nickname, password);
    }
}
