package com.project.taskmanager.repository;

import com.project.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.nickname = ?1 AND u.password = ?2")
    Optional<User> findUserByLoginAndPassword(String login, String password);
    @Query("SELECT u FROM User u WHERE u.nickname = ?1")
    Optional<User> findUserByLogin(String login);
}
