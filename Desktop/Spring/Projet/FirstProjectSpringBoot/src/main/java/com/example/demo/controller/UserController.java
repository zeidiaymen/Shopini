package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.service.Location;
import com.example.demo.service.UserService;

import java.net.SocketException;
import java.util.List;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value="users")
	public List<User> getUsers()
	{	
		return userService.getUsers();
	}
	@PostMapping("addUser")
	public User addAdmin (@RequestBody User user) throws SocketException
	{
		
		return userService.addUser(user);
		
	}
	
	

	
}
