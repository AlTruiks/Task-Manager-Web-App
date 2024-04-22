package com.project.taskmanager.service;

import com.project.taskmanager.model.Task;
import com.project.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public AddTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void SaveTask(Task task) {
        taskRepository.save(task);
    }
    public void DeleteTask(Long taskid) { taskRepository.deleteById(taskid); }
    public void UpdateTaskStatus(Long taskid, Long taskstatus) { taskRepository.updateTaskStatus(taskid, taskstatus); }
}
