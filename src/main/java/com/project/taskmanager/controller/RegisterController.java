package com.project.taskmanager.controller;

import com.project.taskmanager.model.User;
import com.project.taskmanager.repository.UserRepository;
import com.project.taskmanager.service.RegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/registerpage")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public String registerpage() {
        return "registerpage";
    }

    @PostMapping
    private String register(User user, Model model) {
        boolean LoginIsTaken = registerService.CheckIfLoginIsTaken(user.getNickname());
        if (LoginIsTaken) {
            model.addAttribute("errorMessage", "Nickname is already taken");
            return "registerpage";
        }
        else {
            registerService.AddUser(user);
            return "loginpage";
        }
    }
}
