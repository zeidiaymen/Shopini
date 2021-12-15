package com.example.demo.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@DiscriminatorValue("SUPER_ADMIN")
@Entity
public class SUPER_ADMIN extends User  {

	public SUPER_ADMIN() {
	}

	

	
}
