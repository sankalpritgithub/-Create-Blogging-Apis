package com.Blog.Project.Related.SpringBoot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Blog.Project.Related.SpringBoot.Repositories.UserRepo;

@SpringBootTest
class ApplicationTests {
    
	@Autowired
	private UserRepo userRepo;


	@Test
	void contextLoads() {
	}
    
	@Test
	public void repoTest(){
        String ClassName = this.userRepo.getClass().getName();	
	    String PackageName = this.userRepo.getClass().getPackageName(); 
		System.out.println(ClassName);
		System.out.println(PackageName);
	
	}

}
