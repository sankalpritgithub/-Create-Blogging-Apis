package com.Blog.Project.Related.SpringBoot.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Project.Related.SpringBoot.Entites.User;

public interface UserRepo extends JpaRepository<User ,Integer> {
    
    Optional<User> findByEmail(String email);
}
