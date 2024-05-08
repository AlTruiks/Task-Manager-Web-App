package com.project.taskmanager.controller;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Status;
import com.project.taskmanager.service.HomeService;
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

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/homepage")
    public String homepage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "loginpage";
        }
        Long id = (Long) session.getAttribute("userid");
        model.addAttribute("user", homeService.GetUser(id));
        model.addAttribute("projects", homeService.GetProjects());
        model.addAttribute("projectsprint", homeService.GetProjectsToPrint());
        model.addAttribute("statuses", homeService.GetStatusesNames());

        return "homePage";
    }

    @GetMapping("/homepage/addproject")
    private String addProjectPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "loginpage";
        }
        Long id = (Long) session.getAttribute("userid");
        model.addAttribute("user", homeService.GetUser(id));
        model.addAttribute("projects", homeService.GetProjects());
        return "addProjectPage";
    }

    @PostMapping("/homepage/addproject")
    private String addProject(Project project) {
        homeService.SaveProject(project);
        return "redirect:/homepage";
    }

    @PostMapping("/homepage/deleteproject")
    private String deleteProject(Project project) {
        homeService.DeleteProject(project.getProjectid());
        return "redirect:/homepage";
    }

    @GetMapping("/homepage/projectinfo/{projectid}")
    private String infoProject(@PathVariable Long projectid, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "loginpage";
        }
        Long id = (Long) session.getAttribute("userid");
        model.addAttribute("user", homeService.GetUser(id));
        model.addAttribute("projects", homeService.GetProjects());
        model.addAttribute("thisproject", homeService.GetProject(projectid));
        model.addAttribute("tasks", homeService.GetTasksToPrintByProjectId(projectid));
        model.addAttribute("comments", homeService.GetTasksToPrintByProjectId(projectid));
        return "projectInfoPage";
    }
}
