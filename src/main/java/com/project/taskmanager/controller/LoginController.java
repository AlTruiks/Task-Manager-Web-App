package com.project.taskmanager.controller;

import com.project.taskmanager.model.User;
import com.project.taskmanager.repository.UserRepository;
import com.project.taskmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/loginpage")
public class LoginController {
    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String loginpage() {
        return "loginpage";
    }
    @PostMapping
    private String checkLogin(User user, HttpSession session) {
        Optional<User> u = userService.findUserByLoginAndPassword(user.getNickname(), user.getPassword());
        if (u.isPresent()) {
            session.setAttribute("userid", u.get().getUserid());
            return "redirect:/homepage";
        }
        else {
            return "redirect:/loginpage";
        }
    }
}
