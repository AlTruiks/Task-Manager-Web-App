package com.project.taskmanager.service;

import com.project.taskmanager.model.Task;
import com.project.taskmanager.repository.CommentRepository;
import com.project.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    public void SaveTask(Task task) {
        taskRepository.save(task);
    }
    public void DeleteTask(Long taskid) {
        commentRepository.DeleteCommentsByTaskId(taskid);
        taskRepository.deleteById(taskid);
    }
    public void UpdateTaskStatus(Long taskid, Long taskstatus) { taskRepository.updateTaskStatus(taskid, taskstatus); }
}
