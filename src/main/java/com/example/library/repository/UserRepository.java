package com.example.library.repository;

import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(@Param("user_name") String username,@Param("password") String password);
    User findByUsername(@Param("user_name") String username);
 }
