package com.Blog.Project.Related.SpringBoot.Services;

import java.util.List;

import com.Blog.Project.Related.SpringBoot.PlayLoads.CategoryDto;

public interface CategoryService {
    
    // create 
    public CategoryDto createCategory(CategoryDto categoryDto);

   //update 
    public CategoryDto updCategoryDto(CategoryDto categoryDto,Integer categoryId);
//delete
    public void deleteCategory(Integer categoryId);
//get
    public CategoryDto getCategory(Integer categoryId);
//get All
    public List<CategoryDto> getCategories();

}
