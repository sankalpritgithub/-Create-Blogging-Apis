package com.Blog.Project.Related.SpringBoot.Services;

import java.util.List;

import com.Blog.Project.Related.SpringBoot.PlayLoads.PostDto;
import com.Blog.Project.Related.SpringBoot.PlayLoads.PostResponse;



public interface PostService {
     //create posdt 

     PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
   
     //update 
 
     PostDto updatePost(PostDto postDto, Integer postId);
 
    //delete 
     void  deletePost(Integer postId);
     
   
 
    // List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
   public PostResponse  getAllPost(Integer pageNumber, Integer PageSize,String sortBy,String sortDir);
   

 //   PostRemove getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);
 
     // get a single post 
      PostDto  getPostById(Integer postId);
 
      //get all posts by category
     List<PostDto> getPostsByCategory(Integer categoryId);
 
    // get all posts by User 
    List<PostDto> getPostsByUser(Integer userId);
     
    // search Posts
    List<PostDto> searchPosts(String keyword);
 
}
