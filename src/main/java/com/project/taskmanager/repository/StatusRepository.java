package com.project.taskmanager.repository;

import com.project.taskmanager.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT s.statusname FROM Status s")
    List<String> GetStatusesNames();
}
