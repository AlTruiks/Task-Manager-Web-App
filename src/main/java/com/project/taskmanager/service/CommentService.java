package com.project.taskmanager.service;

import com.project.taskmanager.model.Comment;
import com.project.taskmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Object[]> GetCommentsByTaskId(Long taskid) { return commentRepository.GetCommentsByTaskId(taskid); }
    public void SetComment(Comment comment) { commentRepository.save(comment); }
}
