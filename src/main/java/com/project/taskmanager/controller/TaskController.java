package com.project.taskmanager.controller;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Task;
import com.project.taskmanager.service.ProjectService;
import com.project.taskmanager.service.TaskService;
import com.project.taskmanager.service.HomeService;
import com.project.taskmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping("/homepage/addtask/{projectid}")
    private String addtaskpage(@PathVariable Long projectid, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userid = (Long) session.getAttribute("userid");
        model.addAttribute("user", userService.GetUser(userid));
        model.addAttribute("projects", projectService.GetProjects());
        model.addAttribute("thisproject", projectService.GetProject(projectid));
        return "addTaskPage";
    }

    @PostMapping("/addtask/{projectid}")
    private String addtask(@PathVariable Long projectid, Task task) {
        task.setProjectid(projectid);
        task.setCreationdate(LocalDate.now());
        task.setStatus(1L);
        taskService.SaveTask(task);
        return "redirect:/homepage/projectinfo/{projectid}";
    }

    @PostMapping("/deletetask/{projectid}")
    private String deletetask(Task task, @PathVariable Long projectid) {
        taskService.DeleteTask(task.getTaskid());
        return "redirect:/homepage/projectinfo/" + projectid;
    }
    @PostMapping("/updatetask/{projectid}/{taskid}")
    private String updatetask(@PathVariable Long projectid, @PathVariable Long taskid, @RequestParam("taskstatus") Long statusid) {
        taskService.UpdateTaskStatus(taskid, statusid);
        return "redirect:/homepage/projectinfo/{projectid}";
    }
}
