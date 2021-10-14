package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@DiscriminatorValue("LIVREUR")
@Entity
public class Livreur extends User implements Serializable{
	
	private int solde;
	private int pourcentage;

	@OneToOne(mappedBy="livreur")
	Delivery del ;

	public Livreur() {
	}

	public Livreur(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt,String address, String role,int solde,int pourcentage) {
		super(firstName, lastName, email, password,sexe, tel, picture,address, createdAt, role);
		this.solde=solde;
		this.pourcentage=pourcentage;
		
	}
	
	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

}
