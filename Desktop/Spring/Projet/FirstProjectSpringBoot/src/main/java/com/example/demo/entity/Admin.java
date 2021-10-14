package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("ADMIN")
@Entity
public class Admin extends User implements Serializable {

	public Admin() {
	}

	public Admin(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt,String address, String role) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt,address, role);
	}

	
}
