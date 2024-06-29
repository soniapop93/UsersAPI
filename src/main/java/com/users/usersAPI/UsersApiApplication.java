package com.users.usersAPI;

import Database.DatabaseLogic;
import Users.UserLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApiApplication.class, args);
		//UserLogic userLogic =  new UserLogic(new DatabaseLogic());
		//System.out.println(userLogic.test());
	}
}
