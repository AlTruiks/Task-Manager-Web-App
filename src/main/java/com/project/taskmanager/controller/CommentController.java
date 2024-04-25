package com.project.taskmanager.controller;

import com.project.taskmanager.model.Comment;
import com.project.taskmanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/getcomments/{taskid}")
    public ResponseEntity<List<Object[]>> getCommentsForTask(@PathVariable Long taskid) {
        List<Object[]> comments = commentService.GetCommentsByTaskId(taskid);
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/addcomment/{userid}/{taskid}")
    public String addComment(@PathVariable Long userid, @PathVariable Long taskid, String commentcontent) {
        Comment komentarz = new Comment(taskid, userid, commentcontent, LocalDate.now());
        commentService.SetComment(komentarz);
        return "redirect:/homepage";
    }
}
