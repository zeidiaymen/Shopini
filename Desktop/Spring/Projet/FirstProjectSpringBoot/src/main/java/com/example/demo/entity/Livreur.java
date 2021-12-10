package com.example.demo.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@DiscriminatorValue("LIVREUR")
@Entity
public class Livreur extends User  {
	
	private int solde;
	private int pourcentage;

	

	public Livreur() {
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
