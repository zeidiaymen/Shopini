package com.example.demo.service;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import java.util.Date;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

	public List<User> getUsers() {
		return userRepository.findAll();

	}

	public User addUser (User user) throws SocketException
	{	//Password
		String encodedPassword=this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);	
		//CurrentDate
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		user.setCreatedAt(formatter.format(date));
		//Address
		String ipAdress=LocationService.MyIpAdress();
        Address address=LocationService.CurrentLocation(ipAdress);
        user.setAddress(address.getCity());
		
		return userRepository.save(user);		
	}

}
