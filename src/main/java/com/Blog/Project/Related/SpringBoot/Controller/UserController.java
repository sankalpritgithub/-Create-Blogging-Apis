package com.Blog.Project.Related.SpringBoot.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Project.Related.SpringBoot.PlayLoads.ApiResponse;
import com.Blog.Project.Related.SpringBoot.PlayLoads.UserDto;
import com.Blog.Project.Related.SpringBoot.Services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
        UserDto creaUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(creaUserDto,HttpStatus.CREATED);
   }
   @PutMapping("/{userId}")
   public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
      UserDto updatedUser  =    this.userService.updateUser(userDto, uid);
     return ResponseEntity.ok(updatedUser);
}

//ADMIN
//Delete 
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
      this.userService.deleteUser(uid);
//return new ResponseEntity(new ApiResponse("User deleted Successfully", true),HttpStatus.OK);
  return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
}

@GetMapping("/")
public ResponseEntity<List<UserDto>> getAllUsers(){
    
    return ResponseEntity.ok(this.userService.getAllUsers());
}

@GetMapping("/{userId}")
public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
    
    return ResponseEntity.ok(this.userService.getUserById(userId));
}

}
