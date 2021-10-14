package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("ADMIN")

public class Admin extends User {

	public Admin() {
	}

	public Admin(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt, String role) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt, role);
	}

	
}
