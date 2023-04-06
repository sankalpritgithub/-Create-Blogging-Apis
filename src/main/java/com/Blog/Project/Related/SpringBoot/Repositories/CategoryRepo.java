package com.Blog.Project.Related.SpringBoot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Project.Related.SpringBoot.Entites.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    
}
