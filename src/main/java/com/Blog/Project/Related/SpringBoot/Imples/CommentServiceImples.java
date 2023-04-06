package com.Blog.Project.Related.SpringBoot.Imples;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Project.Related.SpringBoot.Entites.Comment;
import com.Blog.Project.Related.SpringBoot.Entites.Post;
import com.Blog.Project.Related.SpringBoot.Exception.ResourceNotFoundException;
import com.Blog.Project.Related.SpringBoot.PlayLoads.CommentDto;
import com.Blog.Project.Related.SpringBoot.Repositories.CommentRepo;
import com.Blog.Project.Related.SpringBoot.Repositories.PostRepo;
import com.Blog.Project.Related.SpringBoot.Services.CommentService;

@Service
public class CommentServiceImples implements CommentService{

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
        Comment comment =  this.modelMapper.map(commentDto, Comment.class);
             comment.setPost(post);
        Comment savedComment  = this.commentRepo.save(comment);
         return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment id", commentId));
        this.commentRepo.delete(com);
    }
    
}
