package com.example.demo.entity;

public class Fournisseur extends User {

	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt, Role role) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt, role);
	}

}
