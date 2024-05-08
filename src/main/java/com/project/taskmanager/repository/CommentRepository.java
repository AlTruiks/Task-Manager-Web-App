package com.project.taskmanager.repository;

import com.project.taskmanager.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c.commentid, c.commentcontent, u.userid, u.nickname, c.dateadded " +
            "FROM Comment c INNER JOIN User u ON c.userid=u.userid " +
            "WHERE c.taskid = ?1")
    List<Object[]> GetCommentsByTaskId(Long taskid);
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.taskid=?1")
    void DeleteCommentsByTaskId(Long taskid);
}
