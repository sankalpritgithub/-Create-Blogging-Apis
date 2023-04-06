package com.Blog.Project.Related.SpringBoot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Project.Related.SpringBoot.Entites.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    
}
