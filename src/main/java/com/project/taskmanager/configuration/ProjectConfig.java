package com.project.taskmanager.configuration;

import com.project.taskmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig implements CommandLineRunner {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectConfig(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        projectRepository.save(new Project("configuration project"));
    }
}
