package com.Blog.Project.Related.SpringBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

import com.Blog.Project.Related.SpringBoot.Exception.ApiException;
import com.Blog.Project.Related.SpringBoot.PlayLoads.JwtAuthRequest;
import com.Blog.Project.Related.SpringBoot.PlayLoads.JwtAuthResponse;
import com.Blog.Project.Related.SpringBoot.PlayLoads.UserDto;
import com.Blog.Project.Related.SpringBoot.Security.JwtTokenHelper;
import com.Blog.Project.Related.SpringBoot.Services.UserService;

@RestController

@RequestMapping(value={"/api/v1/auth"})

public class AuthController {
    

     
   @Autowired
   private JwtTokenHelper jwtTokenHelper;
   @Autowired
   private UserDetailsService userDetailsService;
   @Autowired
   private AuthenticationManager authenticationManager;
   
   @Autowired
   private UserService userService;


   @PostMapping("/login")
   public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
//       passwordEncoder("ABC");
      this.authentication(request.getUsername(),request.getPassword());

    UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

    String token = this.jwtTokenHelper.generateToken(userDetails);

    JwtAuthResponse response = new JwtAuthResponse();
    response.setToken(token);

    return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
}


    public void passwordEncoder(String plainText) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new        BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(plainText);
        System.out.println(password);
    }


@GetMapping("/test")
public String sayhello(){
    return "Hello";
} 


private void authentication(String username, String password) throws Exception {

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
    try{
        this.authenticationManager.authenticate(authenticationToken);
       }catch(BadCredentialsException e){
         System.out.println("Invalid Details !!");
         throw new ApiException("Invalid Username Or Password !!");
       }
      
}


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
         
      UserDto registerUser = this.userService.registerNewUser(userDto);

      return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
    }

   
}
