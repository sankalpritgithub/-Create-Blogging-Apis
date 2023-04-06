package com.Blog.Project.Related.SpringBoot.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Project.Related.SpringBoot.Entites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    
}
