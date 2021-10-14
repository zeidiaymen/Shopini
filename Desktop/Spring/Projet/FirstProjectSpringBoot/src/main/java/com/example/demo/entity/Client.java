package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("CLIENT")

public class Client extends User {

	private Profession profession;
	private CategorieClient categorieClient;

	public Client(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt, String role, Profession profession, CategorieClient categorieClient) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt, role);
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
