package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;

@SuppressWarnings("serial")
@DiscriminatorValue("ADMIN")

public class Admin extends User  {

	public Admin() {
	}

	public Admin(String firstName, String lastName, String email, String password, String sexe, String tel,
			String picture, String createdAt, String address, String accountStatus, String activationToken,
			String twoFactorAuthentication) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt,address,accountStatus,activationToken,twoFactorAuthentication);
	}

	
}
