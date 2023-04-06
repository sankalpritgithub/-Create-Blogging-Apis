package com.Blog.Project.Related.SpringBoot.Imples;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Blog.Project.Related.SpringBoot.Config.AppConstant;
import com.Blog.Project.Related.SpringBoot.Entites.User;
import com.Blog.Project.Related.SpringBoot.Entites.Role;
import com.Blog.Project.Related.SpringBoot.Exception.ResourceNotFoundException;
import com.Blog.Project.Related.SpringBoot.PlayLoads.UserDto;
import com.Blog.Project.Related.SpringBoot.Repositories.RoleRepo;
import com.Blog.Project.Related.SpringBoot.Repositories.UserRepo;
import com.Blog.Project.Related.SpringBoot.Services.UserService;

@Service
public class UserServiceImples implements UserService{

    @Autowired
    private UserRepo userRepo;
    
    @Autowired 
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;
    

    @Override
    public UserDto createUser(UserDto userDto) {
      
        User user = this.dtoToUser(userDto);
       User savUser = this.userRepo.save(user);
          return this.userToDto(savUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
      
    
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));   
        
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
     
        User updateUser  = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updateUser);
        return userDto1;
        
    }

    @Override
    public UserDto getUserById(Integer userId) {
        
        User user = this.userRepo.findById(userId)
        .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
       
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();

      List<UserDto> userDtos =  users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
      return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }


    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
    //   User user = new User();
    //       user.setId(userDto.getId());
    //       user.setName(userDto.getName());
    //       user.setEmail(userDto.getEmail());
    //       user.setAbout(userDto.getAbout());
    //       user.setPassword(userDto.getPassword());
       return user;
    }

    public UserDto userToDto(User user){
      UserDto userDto = this.modelMapper.map(user,UserDto.class);

    //    userDto.setId(user.getId());
    //    userDto.setName(user.getName());
    //    userDto.setEmail(user.getEmail());
    //    userDto.setAbout(user.getAbout());
    //    userDto.setPassword(user.getPassword());
  
      return userDto;

    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
     
     User user = this.modelMapper.map(userDto, User.class);
      // encoded the Password 
     user.setPassword(this.passwordEncoder.encode(user.getPassword()));

       //role 
      Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
         
      user.getRoles().add(role);
      User newUser = this.userRepo.save(user);
          
      return this.modelMapper.map(newUser, UserDto.class);
    }
    
}
