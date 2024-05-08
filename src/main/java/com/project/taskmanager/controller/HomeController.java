package com.project.taskmanager.controller;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Status;
import com.project.taskmanager.service.HomeService;
import com.project.taskmanager.service.ProjectService;
import com.project.taskmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private final HomeService homeService;
    private final UserService userService;
    private final ProjectService projectService;
    @Autowired
    public HomeController(HomeService homeService, UserService userService, ProjectService projectService) {
        this.homeService = homeService;
        this.userService = userService;
        this.projectService = projectService;
    }
    @GetMapping("/homepage")
    public String homepage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "loginpage";
        }
        Long id = (Long) session.getAttribute("userid");
        model.addAttribute("user", userService.GetUser(id));
        model.addAttribute("projects", projectService.GetProjects());
        model.addAttribute("projectsprint", homeService.GetProjectsToPrint());
        model.addAttribute("statuses", homeService.GetStatusesNames());

        return "homepage";
    }
    @GetMapping("/homepage/projectinfo/{projectid}")
    private String projectinfo(@PathVariable Long projectid, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "loginpage";
        }
        Long id = (Long) session.getAttribute("userid");
        model.addAttribute("user", userService.GetUser(id));
        model.addAttribute("projects", projectService.GetProjects());
        model.addAttribute("thisproject", projectService.GetProject(projectid));
        model.addAttribute("tasks", homeService.GetTasksToPrintByProjectId(projectid));
        model.addAttribute("comments", homeService.GetTasksToPrintByProjectId(projectid));
        return "projectInfoPage";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginpage";
    }
}
