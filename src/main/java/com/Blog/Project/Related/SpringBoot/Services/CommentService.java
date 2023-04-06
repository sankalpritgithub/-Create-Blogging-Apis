package com.Blog.Project.Related.SpringBoot.Services;

import com.Blog.Project.Related.SpringBoot.PlayLoads.CommentDto;

public interface CommentService {
    
    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
