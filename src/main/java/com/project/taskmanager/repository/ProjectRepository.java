package com.project.taskmanager.repository;

import com.project.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT " +
            "p.projectid," +
            "p.projectname," +
            "p.description," +
            "p.startdate," +
            "p.enddate," +
            "(SELECT COUNT(t.taskid) FROM Task t WHERE t.projectid = p.projectid AND t.status = 1)," +
            "(SELECT COUNT(t.taskid) FROM Task t WHERE t.projectid = p.projectid AND t.status = 2)," +
            "(SELECT COUNT(t.taskid) FROM Task t WHERE t.projectid = p.projectid AND t.status = 3)," +
            "(SELECT COUNT(t.taskid) FROM Task t WHERE t.projectid = p.projectid AND t.status = 4)" +
            "FROM Project p")
    List<Object[]>GetProjectsToPrint();
}
