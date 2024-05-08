package com.project.taskmanager.service;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.model.Status;
import com.project.taskmanager.model.Task;
import com.project.taskmanager.model.User;
import com.project.taskmanager.repository.ProjectRepository;
import com.project.taskmanager.repository.StatusRepository;
import com.project.taskmanager.repository.TaskRepository;
import com.project.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public HomeService(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository, StatusRepository statusRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
    }


    public List<Object[]> GetProjectsToPrint() { return projectRepository.GetProjectsToPrint(); }
    public List<Status> GetStatusesNames() { return statusRepository.findAll(); }





    public List<Task> GetTasksByProjectId(Long projectid) { return  taskRepository.GetTasksByProjectId(projectid); }
    public List<Object[]> GetTasksToPrintByProjectId(Long projectid) { return  taskRepository.GetTasksToPrintByProjectId(projectid); }
}
