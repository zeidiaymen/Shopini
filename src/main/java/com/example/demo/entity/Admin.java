package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@DiscriminatorValue("ADMIN")
@Entity
public class Admin extends User  {

	public Admin() {
	}

	

	
}
