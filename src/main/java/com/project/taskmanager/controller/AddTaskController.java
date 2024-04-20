package com.project.taskmanager.controller;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Task;
import com.project.taskmanager.service.AddTaskService;
import com.project.taskmanager.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;


@Controller
public class AddTaskController {

    private final AddTaskService addTaskService;
    private final HomeService homeService;

    @Autowired
    public AddTaskController(AddTaskService addTaskService, HomeService homeService) {
        this.addTaskService = addTaskService;
        this.homeService = homeService;
    }

    @RequestMapping("/homepage/addtask/{projectid}")
    private String addtaskpage(@PathVariable Long projectid, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userid = (Long) session.getAttribute("userid");
        model.addAttribute("user", homeService.GetUser(userid));
        model.addAttribute("projects", homeService.GetProjects());
        model.addAttribute("thisproject", homeService.GetProject(projectid));
        return "addTaskPage";
    }

    @PostMapping("/homepage/addtask/{projectid}")
    private String addtask(@PathVariable Long projectid, Task task) {
        task.setProjectid(projectid);
        task.setCreationdate(LocalDate.now());
        task.setStatus(1L);
        addTaskService.SaveTask(task);
        return "redirect:/homepage/projectinfo/{projectid}";
    }

    @PostMapping("/deletetask/{projectid}")
    private String deletetask(Task task, @PathVariable Long projectid) {
        addTaskService.DeleteTask(task.getTaskid());
        return "redirect:/homepage/projectinfo/" + projectid;
    }
}
