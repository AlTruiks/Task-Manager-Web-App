package com.project.taskmanager.repository;

import com.project.taskmanager.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.projectid = ?1")
    List<Task> GetTasksByProjectId(Long projectid);
    @Query("SELECT t.taskid, t.projectid, t.taskname, t.description, p.priorityname, t.creationdate, t.duedate, s.statusname " +
            "FROM Task t " +
            "INNER JOIN Priority p ON t.priority = p.priorityid " +
            "INNER JOIN Status s ON t.status = s.statusid " +
            "WHERE t.projectid = ?1")
    List<Object[]> GetTasksToPrintByProjectId(Long projectid);
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.status = ?2 WHERE t.taskid = ?1")
    void updateTaskStatus(Long taskid, Long taskstatus);
}

