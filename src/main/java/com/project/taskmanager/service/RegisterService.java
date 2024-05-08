package com.project.taskmanager.service;

import com.project.taskmanager.model.User;
import com.project.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean CheckIfLoginIsTaken(String login) {
        Optional<User> u = userRepository.findUserByLogin(login);
        return u.isPresent();
    }
    public void AddUser(User user) {
        userRepository.save(user);
    }
}
