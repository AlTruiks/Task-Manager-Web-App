package com.project.taskmanager.controller;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.service.ProjectService;
import com.project.taskmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }
    @GetMapping("/homepage/addproject")
    private String addProjectPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "loginpage";
        }
        Long id = (Long) session.getAttribute("userid");
        model.addAttribute("user", userService.GetUser(id));
        model.addAttribute("projects", projectService.GetProjects());
        return "addProjectPage";
    }
    @PostMapping("/addproject")
    private String addProject(Project project) {
        projectService.SaveProject(project);
        return "redirect:/homepage";
    }
    @PostMapping("/deleteproject")
    private String deleteProject(Project project) {
        projectService.DeleteProject(project.getProjectid());
        return "redirect:/homepage";
    }
}
