package com.Blog.Project.Related.SpringBoot.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Project.Related.SpringBoot.Entites.Category;
import com.Blog.Project.Related.SpringBoot.Entites.Post;
import com.Blog.Project.Related.SpringBoot.Entites.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
   
    List<Post> findByUser(User user);
    
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String  title);
    
}
