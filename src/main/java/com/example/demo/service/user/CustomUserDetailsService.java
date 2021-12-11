package com.example.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    private User user=null;   

    
    public User getUser() {
		return user;
	}



	



	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElse(null); 
        System.out.println(user.getId());
        this.user=user;
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), (Collection<? extends GrantedAuthority>) new ArrayList<Object>());
    }
}
