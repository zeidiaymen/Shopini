package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@DiscriminatorValue("CLIENT")
@Entity
public class Client extends User {

	private Profession profession;
	private CategorieClient categorieClient;
	
	public Client()
	{
		
	}

	public Client(String firstName, String lastName, String email, String password, String sexe, String tel,
			String picture, String createdAt, String address, String accountStatus, String activationToken,
			String twoFactorAuthentication, Profession profession, CategorieClient categorieClient) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt,address,accountStatus,activationToken,twoFactorAuthentication);
		this.profession = profession;
		this.categorieClient = categorieClient;
	}
	

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public CategorieClient getCategorieClient() {
		return categorieClient;
	}

	public void setCategorieClient(CategorieClient categorieClient) {
		this.categorieClient = categorieClient;
	}

}
