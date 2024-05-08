package com.project.taskmanager.service;

import com.project.taskmanager.model.Project;
import com.project.taskmanager.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> GetProjects() {
        return projectRepository.findAll();
    }
    public Project GetProject(Long id) { return projectRepository.getReferenceById(id);}
    public void SaveProject(Project project) {
        projectRepository.save(project);
    }
    public void DeleteProject(Long id) { projectRepository.deleteById(id); }
}
